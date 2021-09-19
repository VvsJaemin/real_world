package com.example.realworld.chapter3;

import com.example.realworld.chapter2.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}
