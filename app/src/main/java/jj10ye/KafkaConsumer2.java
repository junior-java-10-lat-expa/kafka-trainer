package jj10ye;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer2 {
    @KafkaListener(id = "KafkaConsumer2", topics = MyEvent.TOPIC_NAME, groupId = "consumer-group-2")
    public void consume(MyEvent message) {
        log.info("Consumer consume Kafka message -> {}", message);
    }
}

