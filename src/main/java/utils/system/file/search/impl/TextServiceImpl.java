package utils.system.file.search.impl;

import utils.system.file.search.TextService;

import java.util.Arrays;

public class TextServiceImpl implements TextService {

    @Override
    public int[] indexesOf(char[] text, char[] pattern) {
        int[] pfl = pfl(pattern);
        int[] indexes = new int[text.length];
        int size = 0;
        int k = 0;
        for (int i = 0; i < text.length; ++i) {
            while (pattern[k] != text[i] && k > 0) {
                k = pfl[k - 1];
            }
            if (pattern[k] == text[i]) {
                k = k + 1;
                if (k == pattern.length) {
                    indexes[size] = i + 1 - k;
                    size += 1;
                    k = pfl[k - 1];
                }
            } else {
                k = 0;
            }
        }
        return Arrays.copyOfRange(indexes, 0, size);
    }

    private int[] pfl(char[] text) {
        int[] pfl = new int[text.length];
        pfl[0] = 0;
        for (int i = 1; i < text.length; ++i) {
            int k = pfl[i - 1];
            while (text[k] != text[i] && k > 0) {
                k = pfl[k - 1];
            }
            if (text[k] == text[i]) {
                pfl[i] = k + 1;
            } else {
                pfl[i] = 0;
            }
        }
        return pfl;
    }

}
