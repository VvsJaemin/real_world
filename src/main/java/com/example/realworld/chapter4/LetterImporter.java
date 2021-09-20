package com.example.realworld.chapter4;

import java.io.File;
import java.io.IOException;

public class LetterImporter implements Importer {

    private static final String NAME_PREFIX = "Dear";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile text = new TextFile(file);
        return null;
    }
}
