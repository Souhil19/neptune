package com.somari.ecommerce.notification;

import com.somari.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
