package utils.system.file.file_visitor;

import java.util.List;

public interface FileService {

    List<String> searchInFiles(String search, String pathDir);

    void printDirStructure(String pathDir);

}
