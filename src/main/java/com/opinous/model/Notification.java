package com.opinous.model;

import com.opinous.enums.NotificationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private User user;

    private String text;

    private String url;

    private String image;

    private NotificationStatus notificationStatus;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;

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
