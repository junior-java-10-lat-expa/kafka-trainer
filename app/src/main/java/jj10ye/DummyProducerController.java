package jj10ye;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DummyProducerController {
    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    KafkaAdmin kafkaAdmin;

    @GetMapping("/produce-events")
    void produce(@RequestParam("different-topic-keys") boolean differentKey) {
        kafkaProducer.send100Events(differentKey);
    }

    @GetMapping("/config/{topic-name}")
    String descTopic(@PathVariable("topic-name") String topicName) {
        final Map<String, TopicDescription> descMap = kafkaAdmin.describeTopics(topicName);
        return descMap.values().stream().map(TopicDescription::toString).findAny().orElseThrow();
    }

    @GetMapping("/config/{topic-name}/set-partitions/{partitions-number}")
    void setPartitionsNumber(
            @PathVariable("topic-name") String topicName,
            @PathVariable("partitions-number") Integer partitionsNumber,
            @RequestParam(value = "replication-factor", required = false) Short replicationFactor) {
        kafkaAdmin.createOrModifyTopics(new NewTopic(topicName, Optional.of(partitionsNumber), Optional.ofNullable(replicationFactor)));
        App.restart();
    }
}
