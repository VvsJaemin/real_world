package com.example.realworld.chapter6.examples;

import com.example.realworld.chapter6.Position;
import com.example.realworld.chapter6.Twoot;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class Reduce {

    private final BinaryOperator<Position> maxPosition = BinaryOperator.maxBy(Comparator.comparingInt(Position::getValue));

    Twoot combinTwootsBy(final List<Twoot> twoots, final String senderId, final String newId) {
        return twoots
                .stream()
                .reduce(
                        new Twoot(newId, senderId, "", Position.INITIAL_POSITION),
                        (acc, twoot) -> new Twoot(
                                newId,
                                senderId,
                                twoot.getContent() + acc.getContent(),
                                maxPosition.apply(acc.getPosition(), twoot.getPosition())));
    }

}
