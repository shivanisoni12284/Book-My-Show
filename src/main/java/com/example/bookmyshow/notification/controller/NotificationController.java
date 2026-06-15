package com.example.bookmyshow.notification.controller;

import com.example.bookmyshow.execption.NotificationNotFoundException;
import com.example.bookmyshow.notification.dto.NotificationRequestDto;
import com.example.bookmyshow.notification.dto.NotificationResponseDto;
import com.example.bookmyshow.notification.schema.Notification;
import com.example.bookmyshow.notification.schema.NotificationStatus;
import com.example.bookmyshow.notification.schema.NotificationType;
import com.example.bookmyshow.notification.service.NotificationService;
import com.example.bookmyshow.userlogin.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/notifications")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    public ResponseEntity<NotificationResponseDto> createNotification(@RequestBody NotificationRequestDto notificationRequestDto){
        NotificationResponseDto response = notificationService.createNotification(notificationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable("id")  Long notificationId) {
        NotificationResponseDto responseDto = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(responseDto);
    }

//    public ResponseEntity<List<NotificationResponseDto>> getUserNotifications(User user) {
//    }

    @GetMapping("/status")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotificationByStatus(@RequestParam NotificationStatus notificationStatus){
        List<NotificationResponseDto> responses = notificationService.getAllNotificationByStatus(notificationStatus);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/type")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotificationByType(@RequestParam NotificationType notificationType){
        List<NotificationResponseDto> responses = notificationService.getAllNotificationByType(notificationType);
        return ResponseEntity.ok(responses);
    }
}
