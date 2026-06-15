package com.example.bookmyshow.notification.dto;

import com.example.bookmyshow.notification.schema.NotificationStatus;
import com.example.bookmyshow.notification.schema.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponseDto {

    private Long notificationId;

    private Long userId;

    private String title;

    private String message;

    private NotificationType notificationType;

    private NotificationStatus notificationStatus;

    private LocalDateTime createdAt;
}
