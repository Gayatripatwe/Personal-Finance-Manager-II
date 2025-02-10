package com.example.Personal.Finance.Management.Repository;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import com.example.Personal.Finance.Management.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findByUser_UserId(Long userId);

    List<Notifications> findByUser_UserIdAndNotificationTypeInAndIsReadFalse(Long userId, List<NotificationType> types);
}
