package com.example.bookmyshow.notification.dto;

import com.example.bookmyshow.notification.schema.NotificationStatus;
import com.example.bookmyshow.notification.schema.NotificationType;
import com.example.bookmyshow.userlogin.schema.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationRequestDto {

    private Long userId;

    private String title;

    private String message;

    private NotificationType notificationType;


}
