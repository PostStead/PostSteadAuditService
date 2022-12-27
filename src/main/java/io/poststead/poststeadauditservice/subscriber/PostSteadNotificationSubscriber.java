package io.poststead.poststeadauditservice.subscriber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostSteadNotificationSubscriber {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            name = "io.poststead.queues.events",
                            durable = "true"
                    ),
                    exchange = @Exchange(
                            name = "io.poststead.exchange"
                    ),
                    key = "io.poststead.audit"
            )
    )
    public void processNotification(String eventInfo) {
        try (FileWriter writer = new FileWriter("db.txt", true)) {
            writer.write("At ");
            writer.write(OffsetDateTime.now().toString());
            writer.write(": ");
            writer.write(eventInfo);
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("\n-----------------------------------ERROR-----------------------------------\n");
            System.out.println("Message: " + e.getMessage() + "\n");
            System.out.println("StackTrace: " + Arrays.stream(e.getStackTrace()).toList() + "\n");
            System.out.println("Localized message: " + e.getLocalizedMessage() + "\n\n");
            System.out.println("\n---------------------------------FINISH ERROR---------------------------------\n");
        }
    }

}
