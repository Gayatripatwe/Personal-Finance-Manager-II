package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import com.example.Personal.Finance.Management.Repository.NotificationsRepository;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Service.NotificationsService;
import com.example.Personal.Finance.Management.entity.Notifications;
import com.example.Personal.Finance.Management.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationsServiceimpl implements NotificationsService {
    private final NotificationsRepository notificationsRepository;
    private final UserRepository userRepository;

    public NotificationsServiceimpl(NotificationsRepository notificationsRepository,UserRepository userRepository) {
        this.notificationsRepository = notificationsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notifications createNotification(Notifications notification) {
        Long userId = notification.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        notification.setUser(user);
        return notificationsRepository.save(notification);
    }


    public Optional<Notifications> getNotificationById(Long id) {
        return notificationsRepository.findById(id);
    }


    public List<Notifications> getNotificationsByUserId(Long userId) {
        return notificationsRepository.findByUser_Id(userId);
    }
    public Notifications updateNotification(Long id, Notifications updatedNotification) {
        Notifications existingNotification = notificationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));

        if (updatedNotification.getUser() != null && updatedNotification.getUser().getId() != null) {
            User user = userRepository.findById(updatedNotification.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + updatedNotification.getUser().getId()));
            existingNotification.setUser(user);
        }

        existingNotification.setMessageText(updatedNotification.getMessageText());

        return notificationsRepository.save(existingNotification);
    }

    public void deleteNotification(Long id) {
        if (!notificationsRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id " + id);
        }
        notificationsRepository.deleteById(id);
    }


    @Override
    public void createNotification(User user, String messageText, NotificationType type) {
        Notifications notification = new Notifications(user, messageText, type);
        notificationsRepository.save(notification);
    }

    @Override
    public void markNotificationAsRead(Long notificationId) {
        Notifications notification = notificationsRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationsRepository.save(notification);
    }


}
