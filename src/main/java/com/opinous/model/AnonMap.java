package com.opinous.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnonMap {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomId;

    private Long anonymousUserId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getAnonymousUserId() {
        return anonymousUserId;
    }

    public void setAnonymousUserId(Long anonymousUserId) {
        this.anonymousUserId = anonymousUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
