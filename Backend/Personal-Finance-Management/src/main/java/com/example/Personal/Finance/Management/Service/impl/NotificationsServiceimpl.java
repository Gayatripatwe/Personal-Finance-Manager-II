package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Repository.NotificationsRepository;
import com.example.Personal.Finance.Management.Service.NotificationsService;
import com.example.Personal.Finance.Management.entity.Notifications;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationsServiceimpl implements NotificationsService {
    private final NotificationsRepository notificationsRepository;

    public NotificationsServiceimpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }


    public Notifications createNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }


    public Optional<Notifications> getNotificationById(Long id) {
        return notificationsRepository.findById(id);
    }


    public List<Notifications> getNotificationsByUserId(Long userId) {
        return notificationsRepository.findByUserId(userId);
    }


    public Notifications updateNotification(Long id, Notifications updatedNotification) {
        Optional<Notifications> optionalNotification = notificationsRepository.findById(id);

        if (optionalNotification.isEmpty()) {
            throw new RuntimeException("Notification not found with id " + id);
        }

        Notifications existingNotification = optionalNotification.get();
        existingNotification.setMessageText(updatedNotification.getMessageText());
        existingNotification.setUser(updatedNotification.getUser());

        return notificationsRepository.save(existingNotification);
    }



    public void deleteNotification(Long id) {
        if (!notificationsRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id " + id);
        }
        notificationsRepository.deleteById(id);
    }


}
