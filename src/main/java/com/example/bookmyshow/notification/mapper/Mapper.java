package com.example.bookmyshow.notification.mapper;

import com.example.bookmyshow.notification.dto.NotificationResponseDto;
import com.example.bookmyshow.notification.schema.Notification;

public class Mapper {

    public NotificationResponseDto converToDto(Notification notification){

        return NotificationResponseDto.builder()
                .notificationId(notification.getNotificationId())
                .userId(notification.getUser().getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .notificationType(notification.getNotificationType())
                .notificationStatus(notification.getNotificationStatus())
                .createdAt(notification.getCreatedAt())
                .build();

    }
}
