package com.opinous.model;

import com.opinous.enums.NotificationStatus;
import com.opinous.model.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_active=1")
public class Notification extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String text;

    private String url;

    private String image;

    private NotificationStatus notificationStatus;
}
