package utils.system.file.search.impl;

import org.junit.jupiter.api.Test;
import utils.system.file.search.TextService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {
    TextService service = new TextServiceImpl();

    @Test
    void indexesOf() {
        int[] indexes = service.indexesOf("sadbutsad".toCharArray(), "sad".toCharArray());
        assertEquals("[0, 6]", Arrays.toString(indexes));
    }
}