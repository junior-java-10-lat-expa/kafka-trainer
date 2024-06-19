package jj10ye;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaConsumer1 {
    @KafkaListener(id = "KafkaConsumer1", topics = MyEvent.TOPIC_NAME, groupId = "consumer-group-1")
    public void consume(MyEvent message) {
        log.info("Consumer consume Kafka message -> {}", message);
    }
}

