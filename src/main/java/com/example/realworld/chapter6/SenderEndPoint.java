package com.example.realworld.chapter6;

import java.util.Objects;

public class SenderEndPoint {

    private final User user;
    private final Twootr twootr;

    public SenderEndPoint(final User user, final Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(user, userIdToFollow);

        return twootr.onFollow(user, userIdToFollow);
    }

    public Position onSendTwoot(final String id, final String content) {
        Objects.requireNonNull(content, "content");

        return twootr.onSendTwoot(id, user, content);
    }

    public void onLogOff() {
        user.onLogoff();
    }

    public DeleteStatus onDeleteStatus(final String id) {
        return twootr.onDeleteTwoot(user.getId(), id);
    }
}
