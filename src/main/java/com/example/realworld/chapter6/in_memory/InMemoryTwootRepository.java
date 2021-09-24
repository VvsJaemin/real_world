package com.example.realworld.chapter6.in_memory;

import com.example.realworld.chapter6.Position;
import com.example.realworld.chapter6.Twoot;
import com.example.realworld.chapter6.TwootQuery;
import com.example.realworld.chapter6.TwootRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class InMemoryTwootRepository implements TwootRepository {

    private final List<Twoot> twoots = new ArrayList<>();

    private Position currentPosition = Position.INITIAL_POSITION;

    @Override
    public Twoot add(String id, String userId, String content) {

        currentPosition = currentPosition.next();

        var twootPosition = currentPosition;
        var twoot = new Twoot(id, userId, content, twootPosition);
        twoots.add(twoot);

        return twoot;
    }

    @Override
    public Optional<Twoot> get(String id) {
        return twoots
                .stream()
                .filter(twoot -> twoot.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(Twoot twoot) {
        twoots.remove(twoot);
    }

    @Override
    public void query(TwootQuery twootQuery, Consumer<Twoot> callback) {
        if (!twootQuery.hasUsers()) {
            return;
        }

        var lastSeenPosition = twootQuery.getLastSeenPosition();
        var inUsers = twootQuery.getInUsers();

        twoots.stream()
                .filter(twoot -> inUsers.contains(twoot.getSenderId()))
                .filter(twoot-> twoot.isAfter(lastSeenPosition))
                .forEach(callback);
    }


    public void queryLoop(final TwootQuery twootQuery, final Consumer<Twoot> callbacks) {
        if (!twootQuery.hasUsers()) {
            return;
        }

        var lastSeenPosition = twootQuery.getLastSeenPosition();
        var inUsers = twootQuery.getInUsers();

        for (Twoot twoot : twoots) {
            if (inUsers.contains(twoot.getSenderId()) &&
                    twoot.isAfter(lastSeenPosition)) {
                callbacks.accept(twoot);
            }
        }
    }

    @Override
    public void clear() {
        twoots.clear();
    }
}
