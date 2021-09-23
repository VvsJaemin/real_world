package com.example.realworld.chapter6.examples;

import com.example.realworld.chapter6.ReceiverEndPoint;
import com.example.realworld.chapter6.Twoot;

public class PrintingEndPoint implements ReceiverEndPoint {

    @Override
    public void onTwoot(final Twoot twoot) {
        System.out.println(twoot.getSenderId() + ":" + twoot.getContent());

    }
}
