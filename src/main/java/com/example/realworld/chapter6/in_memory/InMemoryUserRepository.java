package com.example.realworld.chapter6.in_memory;

import com.example.realworld.chapter6.FollowStatus;
import com.example.realworld.chapter6.User;
import com.example.realworld.chapter6.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> userIdToUser = new HashMap<>();

    @Override
    public boolean add(User user) {

        return userIdToUser.putIfAbsent(user.getId(), user)==null;
    }

    @Override
    public Optional<User> get(String userId) {
        return Optional.ofNullable(userIdToUser.get(userId));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public FollowStatus follow(User follower, User userToFollow) {

        return userToFollow.addFollow(follower);
    }

    public void clear() {
        userIdToUser.clear();
    }

    public void close() {

    }
}
