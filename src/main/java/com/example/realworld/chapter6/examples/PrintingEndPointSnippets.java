package com.example.realworld.chapter6.examples;

import com.example.realworld.chapter6.ReceiverEndPoint;
import com.example.realworld.chapter6.Twoot;

public class PrintingEndPointSnippets {
    public static void main(String[] args) {
        final ReceiverEndPoint anonymousClass = new ReceiverEndPoint(){
            @Override
            public void onTwoot(final Twoot twoot) {
                System.out.println(twoot.getSenderId() + ":" + twoot.getContent());

            }
        };

        //lambda
        final ReceiverEndPoint lambda =
                twoot -> System.out.println(twoot.getSenderId() + ":" + twoot.getContent());
    }
}
