package io.poststead.poststeadauditservice.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostSteadNotificationSubscriber {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            name = "io.poststead.events"
                    ),
                    exchange = @Exchange(
                            name = "io.poststead"
                    )
            )
    )
    public void processNotification(String eventInfo) {
        try (PrintWriter writer = new PrintWriter("db.txt")) {
            writer.println(eventInfo);
        } catch (Exception e) {
            System.out.println("\n-----------------------------------ERROR-----------------------------------\n");
            System.out.println("Message: " + e.getMessage() + "\n");
            System.out.println("StackTrace: " + Arrays.stream(e.getStackTrace()).toList() + "\n");
            System.out.println("Localized message: " + e.getLocalizedMessage() + "\n\n");
            System.out.println("\n---------------------------------FINISH ERROR---------------------------------\n");
        }
    }

}
