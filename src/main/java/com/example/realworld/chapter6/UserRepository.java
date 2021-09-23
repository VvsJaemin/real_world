package com.example.realworld.chapter6;

import java.util.Optional;

public interface UserRepository {
    boolean add(User user);

    Optional<User> get(String userId);

    void update(User user);

    FollowStatus follow(User follower, User userToFollow);

}
