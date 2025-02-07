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


    @PostMapping
    public ResponseEntity<Notifications> createNotification(@RequestBody Notifications notification) {
        Notifications savedNotification = notificationsService.createNotification(notification);
        return ResponseEntity.ok(savedNotification);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Notifications> getNotificationById(@PathVariable Long id) {
        Optional<Notifications> notification = notificationsService.getNotificationById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notifications>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notifications> notifications = notificationsService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notifications> updateNotification(
            @PathVariable Long id, @RequestBody Notifications updatedNotification) {
        Notifications updated = notificationsService.updateNotification(id, updatedNotification);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        notificationsService.deleteNotification(id);
        return ResponseEntity.ok("Notification deleted successfully");
    }

}
