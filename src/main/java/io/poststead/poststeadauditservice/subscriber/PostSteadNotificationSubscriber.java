package io.poststead.poststeadauditservice.subscriber;

import io.poststead.poststeadauditservice.repository.EventInfoRepository;
import io.poststead.poststeadauditservice.subscriber.model.EventInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostSteadNotificationSubscriber {

    private final EventInfoRepository eventInfoRepository;

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
        eventInfoRepository.save(
            EventInfoEntity.builder()
                .content(eventInfo)
                .build()
        );
    }

}
