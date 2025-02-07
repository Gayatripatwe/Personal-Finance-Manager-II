package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import com.example.Personal.Finance.Management.entity.Notifications;
import com.example.Personal.Finance.Management.entity.User;

import java.util.List;
import java.util.Optional;

public interface NotificationsService {
    public Notifications createNotification(Notifications notification);
    public Optional<Notifications> getNotificationById(Long id);
    public List<Notifications> getNotificationsByUserId(Long userId);
    public Notifications updateNotification(Long id, Notifications updatedNotification);
    public void deleteNotification(Long id);
    void createNotification(User user, String messageText, NotificationType type);
    void markNotificationAsRead(Long notificationId);
}
