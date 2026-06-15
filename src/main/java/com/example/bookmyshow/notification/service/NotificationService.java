package com.example.bookmyshow.notification.service;

import com.example.bookmyshow.booking.schema.Booking;
import com.example.bookmyshow.execption.NotificationNotFoundException;
import com.example.bookmyshow.notification.dto.NotificationRequestDto;
import com.example.bookmyshow.notification.mapper.Mapper;
import com.example.bookmyshow.notification.repository.NotificationRepository;
import com.example.bookmyshow.notification.dto.NotificationResponseDto;
import com.example.bookmyshow.notification.schema.Notification;
import com.example.bookmyshow.notification.schema.NotificationStatus;
import com.example.bookmyshow.notification.schema.NotificationType;
import com.example.bookmyshow.refund.schema.Refund;
import com.example.bookmyshow.userlogin.schema.User;
import com.example.bookmyshow.userlogin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final Mapper mapper;

    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto){
        //validate user
        User user = userService.getUserById(notificationRequestDto.getUserId());

        Notification notification = Notification.builder()
                .user(user)
                .title(notificationRequestDto.getTitle())
                .message(notificationRequestDto.getMessage())
                .notificationStatus(NotificationStatus.PENDING)
                .notificationType(notificationRequestDto.getNotificationType())
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        notification.setNotificationStatus(NotificationStatus.SENT);

        notificationRepository.save(notification);

        return mapper.converToDto(notification);




    }

    public NotificationResponseDto getNotificationById(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()-> new NotificationNotFoundException("notification with id not found :"+notificationId));
        return mapper.converToDto(notification);
    }

    public List<NotificationResponseDto> getUserNotifications(User user){
        Long userId = user.getId();
        userService.getUserById(userId);
        List<Notification> notifications = notificationRepository.findByUser(user);
        return notifications.stream().map(mapper::converToDto).toList();
    }

    public List<NotificationResponseDto> getAllNotificationByStatus(NotificationStatus notificationStatus){
        List<Notification> notificationList = notificationRepository.findByNotificationStatus(notificationStatus);
        return notificationList.stream().map(mapper:: converToDto).toList();

    }

    public List<NotificationResponseDto> getAllNotificationByType(NotificationType notificationType){
        List<Notification> notificationList = notificationRepository.findByNotificationType(notificationType);
        return notificationList.stream().map(mapper:: converToDto).toList();

    }

    public NotificationResponseDto sendBookingCancellation(Booking booking){

        Notification notification = Notification.builder()
                .user(booking.getUser())
                .title("Booking Cancelled")
                .message("Your booking has been cancelled, Refund will be processed shortly")
                .notificationType(NotificationType.BOOKING_CANCELLED)
                .notificationStatus(NotificationStatus.SENT)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        return mapper.converToDto(notification);

    }

    public NotificationResponseDto sendRefundInitiated(Refund refund){

        Notification notification = Notification.builder()
                .user(refund.getBooking().getUser())
                .title("Refund Initiated")
                .message("Your refund of ₹"+refund.getRefundAmount()+"has been initiated")
                .notificationType(NotificationType.REFUND_INITIATED)
                .notificationStatus(NotificationStatus.SENT)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        return mapper.converToDto(notification);

    }

    public NotificationResponseDto sendRefundCompleted(Refund refund){

        Notification notification = Notification.builder()
                .user(refund.getBooking().getUser())
                .title("Refund Completed")
                .message("Your refund of ₹"+refund.getRefundAmount()+"has been completed")
                .notificationType(NotificationType.REFUND_COMPLETED)
                .notificationStatus(NotificationStatus.SENT)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        return mapper.converToDto(notification);
        
    }


    public NotificationResponseDto  sendBookingConfirmation(Booking booking) {

        Notification notification = Notification.builder()
                .user(booking.getUser())
                .title("Booking Confirmed")
                .message("Your booking has been completed")
                .notificationType(NotificationType.BOOKING_CONFIRMED)
                .notificationStatus(NotificationStatus.SENT)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        return mapper.converToDto(notification);
    }
}
