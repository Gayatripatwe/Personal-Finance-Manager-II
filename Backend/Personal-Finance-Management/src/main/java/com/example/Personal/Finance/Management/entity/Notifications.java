package com.example.Personal.Finance.Management.entity;

import com.example.Personal.Finance.Management.Enum.NotificationType;
import jakarta.persistence.*;

@Entity
@Table(name="Notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String messageText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // ✅ Ensure correct mapping
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(nullable = false)
    private boolean isRead = false;

    public Notifications(Long id, String messageText, User user) {
        this.id = id;
        this.messageText = messageText;
        this.user = user;
    }

    public Notifications() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void setRead(boolean isRead) { this.isRead = isRead; }

    public Notifications(NotificationType notificationType, boolean isRead) {
        this.notificationType = notificationType;
        this.isRead = isRead;
    }

    public Notifications(User user, String messageText, NotificationType notificationType) {
        this.user = user;
        this.messageText = messageText;
        this.notificationType = notificationType;
    }
}
