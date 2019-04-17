package com.opinous.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.opinous.model.common.BaseEntity;

@Entity
@Getter
@Setter
@ToString
public class Follow extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "followerId")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followingId")
    private User following;

    public Follow() {

    }

    public Follow(final User follower, final User following) {
        this.follower = follower;
        this.following = following;

    }
}
