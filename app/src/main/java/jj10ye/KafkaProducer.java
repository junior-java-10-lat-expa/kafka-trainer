package jj10ye;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, MyEvent> kafkaTemplate;

    public void send100Events(boolean diffKeys) {
        for (int i = 0; i < 100; i++) {
            LocalDateTime now = LocalDateTime.now();
            MyEvent event = new MyEvent(diffKeys ? i + now.toString() : null);
            kafkaTemplate.send(MyEvent.TOPIC_NAME, event.getKey(), event);
            log.info("Producer produced the message {}", event);
        }
    }
}

