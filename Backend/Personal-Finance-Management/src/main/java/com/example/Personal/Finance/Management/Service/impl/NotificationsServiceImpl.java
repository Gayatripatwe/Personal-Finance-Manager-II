package com.example.Personal.Finance.Management.Service.impl;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import com.example.Personal.Finance.Management.Repository.NotificationsRepository;
import com.example.Personal.Finance.Management.Service.NotificationsService;
import com.example.Personal.Finance.Management.entity.Notifications;
import com.example.Personal.Finance.Management.entity.User;
import com.example.Personal.Finance.Management.entity.SavingGoals;
import com.example.Personal.Finance.Management.entity.Expenses;
import com.example.Personal.Finance.Management.entity.Income;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationsServiceImpl implements NotificationsService {
    private final NotificationsRepository notificationsRepository;

    public NotificationsServiceImpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    public Notifications createNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }

    @Override
    public Optional<Notifications> getNotificationById(Long id) {
        return notificationsRepository.findById(id);
    }

    @Override
    public List<Notifications> getNotificationsByUserId(Long userId) {
        return notificationsRepository.findByUser_UserId(userId); // ✅ Correct field name
    }



    @Override
    public Notifications updateNotification(Long id, Notifications updatedNotification) {
        Notifications existingNotification = notificationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));

        if (updatedNotification.getUser() != null && updatedNotification.getUser().getUserid() != 0) {
            existingNotification.setUser(updatedNotification.getUser());
        }

        existingNotification.setMessageText(updatedNotification.getMessageText());

        return notificationsRepository.save(existingNotification);
    }

    @Override
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

    @Override
    public void notifyIncomeAdded(Income income) {
        String message = "Income of ₹" + income.getAmount() + " has been added.";
        createNotification(income.getUser(), message, NotificationType.INCOME_UPDATE);
    }

    // ✅ Fix: Properly implements 'notifyExpenseAdded()' with correct entity
    @Override
    public void notifyExpenseAdded(Expenses expense) {
        String message = "Expense of ₹" + expense.getAmount() + " has been recorded.";
        createNotification(expense.getUser(), message, NotificationType.EXPENSE_UPDATE);
    }

    @Override
    public void notifySavingsGoalProgress(SavingGoals savingGoal) {
        double progress = (savingGoal.getCurrentAmount() / savingGoal.getTargetAmount()) * 100;
        if (progress >= 50) {
            String message = "You've achieved 50% of your savings goal: " + savingGoal.getGoalName();
            createNotification(savingGoal.getUser(), message, NotificationType.SAVINGS_GOAL_UPDATE);
        }
    }
}
