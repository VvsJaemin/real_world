package com.example.realworld.chapter6;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.example.realworld.chapter6.DeleteStatus.NOT_YOUR_TWOOT;
import static com.example.realworld.chapter6.DeleteStatus.UNKNOWN_TWOOT;
import static com.example.realworld.chapter6.FollowStatus.INVALID_USER;
import static com.example.realworld.chapter6.FollowStatus.SUCCESS;
import static com.example.realworld.chapter6.Position.INITIAL_POSITION;

public class Twootr {
    private final TwootRepository twootRepository;
    private final UserRepository userRepository;

    public Twootr(TwootRepository twootRepository, UserRepository userRepository) {
        this.twootRepository = twootRepository;
        this.userRepository = userRepository;
    }

    public Optional<SenderEndPoint> onLogon(final String userId, final String password, final ReceiverEndPoint receiverEndPoint) {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(password, "password");

        var authenticatedUser = userRepository
                .get(userId)
                .filter(userOfSameId -> {
                    var hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                    return Arrays.equals(hashedPassword, userOfSameId.getPassword());
                });

        authenticatedUser.ifPresent(user -> {
            user.onLogon(receiverEndPoint);
            twootRepository.query(
                    new TwootQuery()
                            .inUsers(user.getFollowing())
                            .lastSeenPosition(user.getLastSeenPosition()),
                    user::receiveTwoot
            );
            userRepository.update(user);
        });

        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
    }

    public RegistrationStatus onRegisterUser(final String userId, final String password) {
        var salt = KeyGenerator.newSalt();
        var hashedPassword = KeyGenerator.hash(password, salt);
        var user = new User(userId, hashedPassword, salt, INITIAL_POSITION);
        return userRepository.add(user) ? RegistrationStatus.SUCCESS : RegistrationStatus.DUPLICATE;
    }

    FollowStatus onFollow(final User follow, final String userIdToFollow) {
        return userRepository.get(userIdToFollow)
                .map(userToFollow -> userRepository.follow(follow, userToFollow))
                .orElse(INVALID_USER);
    }

    Position onSendTwoot(final String id, final User user, final String content) {
        var userId = user.getId();
        var twoot = twootRepository.add(id, userId, content);

        user.followers()
                .filter(User::isLoggedOn)
                .forEach(follower -> {
                    follower.receiveTwoot(twoot);
                    userRepository.update(follower);
                });

        return twoot.getPosition();
    }

    DeleteStatus onDeleteTwoot(final String userId, final String id) {
        return (DeleteStatus) twootRepository
                .get(id)
                .map(twoot ->
                {
                    var canDeleteTwoot = twoot.getSenderId().equals(userId);
                    if (canDeleteTwoot) {
                        twootRepository.delete(twoot);
                    }
                    return canDeleteTwoot ? SUCCESS : NOT_YOUR_TWOOT;
                })
                .orElse(UNKNOWN_TWOOT);
    }
}
