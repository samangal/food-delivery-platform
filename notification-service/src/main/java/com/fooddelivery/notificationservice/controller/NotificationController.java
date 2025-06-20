package com.fooddelivery.notificationservice.controller;

import com.fooddelivery.notificationservice.model.Notification;
import com.fooddelivery.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String sendNotification(@RequestBody Notification notification) {
        notificationService.send(notification);
        return "Notification sent for Order ID: " + notification.getOrderId();
    }
}
