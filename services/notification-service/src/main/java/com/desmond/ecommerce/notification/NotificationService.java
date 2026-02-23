package com.desmond.ecommerce.notification;


import com.desmond.ecommerce.NotificationServiceApplication;
import com.desmond.ecommerce.kafkaConsumer.OrderConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import static java.lang.String.format;

@Service
public class NotificationService {
    public static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "order-topic")
    public void consumerOrderConfirmationNoticiations(OrderConfirmation orderConfirmation) {
        logger.info(format("Consuming message from order-topic Topic: %s", orderConfirmation));

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(orderConfirmation.email());
            messageHelper.setSubject(String.format("Your Order with OrderReference %s is placed successfully", orderConfirmation.orderReference()));
            messageHelper.setText(String.format("""
                    Hi
                    
                    Your order with order number %s is now placed successfully. The total amount is %s.
                    
                    Best Regards,
                    Spring Shop
                    """,
                    orderConfirmation.orderReference(),
                    orderConfirmation.totalAmount()
            ));
        };
        try {
            javaMailSender.send(messagePreparator);
            logger.info("Order Notification email send!");
        } catch (MailException e) {
            logger.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail", e);
        }
    }

}
