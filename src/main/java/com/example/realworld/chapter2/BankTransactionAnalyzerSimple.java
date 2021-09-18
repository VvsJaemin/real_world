package com.example.realworld.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCE = "src/main/resource";

    public static void main(String ... args) throws IOException {
        final Path path = Paths.get(RESOURCE + args[0]);

        final List<String> lines = Files.readAllLines(path);

        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if(date.getMonth()== Month.JANUARY){
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        System.out.println("1월 총 거래액은 : " + total);
    }
}