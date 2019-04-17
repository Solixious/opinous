package com.opinous.model;

import com.opinous.enums.NotificationStatus;
import com.opinous.model.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@Where(clause = "is_active=1")
public class Notification extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String text;

    private String url;

    private String image;

    private NotificationStatus notificationStatus;

    public Notification() {

    }

    public Notification(final User user, final String text, final String url, final String image) {
        this.user = user;
        this.text = text;
        this.url = url;
        this.image = image;
        this.notificationStatus = NotificationStatus.unread;
    }
}
