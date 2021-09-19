package com.example.realworld.chapter3;

import com.example.realworld.chapter2.BankStatementAnalyzer;
import com.example.realworld.chapter2.BankStatementCSVParser;
import com.example.realworld.chapter2.BankStatementParser;

import java.io.IOException;

public class MainApplication {
    public static void main(final String ...args) throws IOException {
        final com.example.realworld.chapter2.BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
