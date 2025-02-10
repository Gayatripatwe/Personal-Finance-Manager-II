package com.example.Personal.Finance.Management.Controller;

import com.example.Personal.Finance.Management.Service.NotificationsService;
import com.example.Personal.Finance.Management.entity.Notifications;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/notifications")
public class NotificationsController {
    private final NotificationsService notificationsService;

    public NotificationsController(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notifications> getNotificationById(@PathVariable Long id) {
        Optional<Notifications> notification = notificationsService.getNotificationById(id);
        return notification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notifications>> getNotificationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationsService.getNotificationsByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        notificationsService.deleteNotification(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }
}
