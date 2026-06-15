package com.example.bookmyshow.notification.repository;

import com.example.bookmyshow.notification.dto.NotificationResponseDto;
import com.example.bookmyshow.notification.schema.Notification;
import com.example.bookmyshow.notification.schema.NotificationStatus;
import com.example.bookmyshow.notification.schema.NotificationType;
import com.example.bookmyshow.userlogin.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUser(User user);

    List<Notification> findByNotificationStatus(NotificationStatus notificationStatus);

    List<Notification> findByNotificationType(NotificationType notificationtype);



}
