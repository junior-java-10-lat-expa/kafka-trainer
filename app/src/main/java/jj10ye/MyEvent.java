package jj10ye;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
// for Deserialization
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class MyEvent {
    public static final String TOPIC_NAME = "my-topic";
    private String key;
}
