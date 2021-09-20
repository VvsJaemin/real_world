package com.example.realworld;

import com.example.realworld.chapter2.BankStatementCSVParser;
import com.example.realworld.chapter2.BankStatementParser;
import com.example.realworld.chapter2.BankTransaction;
import com.example.realworld.chapter4.Attributes;
import com.example.realworld.chapter4.Document;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static java.awt.PageAttributes.MediaType.LETTER;

public class RealworldApplicationTests {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        String line = "30-01-2017,-50,Tesco";

        BankTransaction result = statementParser.parseFrom(line);

        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }


}
