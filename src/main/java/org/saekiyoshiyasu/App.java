package org.saekiyoshiyasu;

import org.apache.kafka.streams.KafkaStreams;

/**
 * @author laclefyoshi
 */
public class App {
    
    public static void main(String[] args) {
        startWordCount();
    }

    private static void startWordCount() {
        WordCountStreamsBuilder builder =
            new WordCountStreamsBuilder("127.0.0.1:9092", "127.0.0.1:2181",
                                        "input-stream", "output-stream");
        KafkaStreams streams = builder.build();
        streams.start();
    }
}
