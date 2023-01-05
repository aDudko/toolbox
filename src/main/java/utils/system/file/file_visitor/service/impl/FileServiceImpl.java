package utils.system.file.file_visitor.service.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import utils.system.file.file_visitor.service.FileService;

import java.nio.charset.MalformedInputException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    @SneakyThrows
    public List<String> searchInFiles(String search, String pathDir) {
        List<String> result = new ArrayList<>();
        Path path = Paths.get(pathDir);
        Files.walkFileTree(path, new MyFileVisitor(search, result));
        return result;
    }

    @Override
    @SneakyThrows
    public void printDirStructure(String pathDir) {
        Path path = Paths.get(pathDir);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                int count = dir.getNameCount() - path.getNameCount() + 1;
                count += dir.getFileName().toString().length();
                // Выравнивание по правому краю
                String text = String.format("%" + count + "s", dir.getFileName());
                text = text.replaceAll("[\\s]", "-");
                System.out.println(text);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @AllArgsConstructor
    private class MyFileVisitor extends SimpleFileVisitor<Path> {

        private final String SEARCH_STRING;
        private List<String> result;

        @Override
        @SneakyThrows
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            List<String> lines;
            try {
                lines = Files.readAllLines(file);
            } catch (MalformedInputException e) {
                return FileVisitResult.CONTINUE;
            }
            for (String s: lines) {
                if (s.contains(SEARCH_STRING)) {
                    result.add(file.toAbsolutePath().toUri().getPath());
                    break;
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
