package com.fooddelivery.notificationservice.service;

import com.fooddelivery.notificationservice.model.Notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void send(Notification notification) {
        System.out.println("Sending notification: " + notification.getMessage());
    }
    
    @RabbitListener(queues = "order.queue")
    public void receiveNotification(Notification notification) {
        System.out.println("Received notification: " + notification.getMessage() + " for Order ID: " + notification.getOrderId());
    }
}
