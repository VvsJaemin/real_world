package com.example.realworld.chapter3;

import com.example.realworld.chapter2.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
