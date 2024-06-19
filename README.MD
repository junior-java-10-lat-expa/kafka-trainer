# Instrukcja uruchomienia:

Pre-Requirements: Docker, Idea, Java 21

1. cd [app/src/main/resources/kafka-docker](app/src/main/resources/kafka-docker) && docker-compose up
2. start [app/src/main/java/jj10ye/App.Java](app/src/main/java/jj10ye/App.java)

Gotowe!

# Endpoints
- info o topicu `my-topic` (jedyny na start): http://localhost:8080/config/my-topic
- info o topicu w Kafka UI (wystartowane przez docker-compose) http://localhost:8090/ui/clusters/local/all-topics/my-topic
- Emisja 100 eventów: (GET) http://localhost:8080/produce-events
- Modyfikacja Topicu: (GET) http://localhost:8080/config/{nazwa-topicu}/set-partitions/{liczba-partycji}, np: http://localhost:8080/config/my-topic/set-partitions/2

# Uwagi:

- w [docker-compose.yaml](app/src/main/resources/kafka-docker/docker-compose.yaml) jest zdefiniowany  1 topic `my-topic` z 1 repliką i 1 partycją
- kafka wstaje na porcie 29092
- port kafki ustawiony w  [application.yml](app/src/main/resources/application.yml)
- [MyEvent](app/src/main/java/jj10ye/MyEvent.java) jest w pakiecie [jj10ye](app/src/main/java/jj10ye) i ten pakiet jest wskazany w [application.yml](app/src/main/resources/application.yml)
- [MyEvent](app/src/main/java/jj10ye/MyEvent.java) musi mieć nazwę topicu `my-topic` bo tak jest ustawione w [docker-compose.yaml](app/src/main/resources/kafka-docker/docker-compose.yaml)
- ConsumerGroup zdefiniowany w klasach KafkaConsumer1/2/3, np: [KafkaConsumer1](app/src/main/java/jj10ye/KafkaConsumer1.java). Nazwa grupy dowolna (zarejestruje się przy wstawaniu). Tu: `consumer-group-1`.
- Consumer, np [KafkaConsumer1](app/src/main/java/jj10ye/KafkaConsumer1.java) w adnotacji `@KafkaListener` musi podać istniejącą nazwę topicu. W tym wypadku jedyny zarejestrowany na start to "my-topic" w [docker-compose.yaml](app/src/main/resources/kafka-docker/docker-compose.yaml).
- Producer, tu tylko [KafkaProducer](app/src/main/java/jj10ye/KafkaProducer.java) tak samo jak powyżej, podaje nazwę topicu `my-topic`. Robi to przy wysyłce, np` kafkaTemplate.send(MyEvent.TOPIC_NAME, key , event);`