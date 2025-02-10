package com.example.Personal.Finance.Management.Service;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import com.example.Personal.Finance.Management.entity.Notifications;
import com.example.Personal.Finance.Management.entity.User;
import com.example.Personal.Finance.Management.entity.SavingGoals;
import com.example.Personal.Finance.Management.entity.Expenses;
import com.example.Personal.Finance.Management.entity.Income;

import java.util.List;
import java.util.Optional;

public interface NotificationsService {
    Notifications createNotification(Notifications notification);
    Optional<Notifications> getNotificationById(Long id);
    List<Notifications> getNotificationsByUserId(Long userId);
    Notifications updateNotification(Long id, Notifications updatedNotification);
    void deleteNotification(Long id);
    void createNotification(User user, String messageText, NotificationType type);
    void markNotificationAsRead(Long notificationId);
    void notifyIncomeAdded(Income income);
    void notifyExpenseAdded(Expenses expense);  // ✅ Ensure this is 'Expenses'
    void notifySavingsGoalProgress(SavingGoals savingGoal);
}
