package org.saekiyoshiyasu;

import org.apache.kafka.streams.KafkaStreams;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.kafka.streams.state.StreamsMetadata;
import java.util.Collection;

/**
 * @author laclefyoshi
 */
public class App {

    private static String brokers = "127.0.0.1:9092";
    private static String zookeepers = "127.0.0.1:2181";
    private static String inputStream = "input-stream";
    private static String outputStream = "output-stream";
    private static String storeName = "Counts";
    private static String applicationHost = "127.0.0.1";
    private static int applicationPort = 18080;
    private static String stateDirectory = "kafka-streams-state";

    public static void main(String[] args) {
        // startWordCount();
        startQueryableWordCount();
    }

    private static void startWordCount() {
        WordCountStreamsBuilder builder =
            new WordCountStreamsBuilder(brokers, zookeepers,
                                        inputStream, outputStream,
                                        storeName);
        KafkaStreams streams = builder.build();
        streams.start();
    }

    private static void startQueryableWordCount() {
        QueryableWordCountStreamsBuilder builder =
            new QueryableWordCountStreamsBuilder(brokers, zookeepers,
                                                 inputStream, outputStream,
                                                 storeName,
                                                 applicationHost, applicationPort,
                                                 stateDirectory);
        KafkaStreams streams = builder.build();
        streams.start();

        for (int i = 0; i < 10; i++) {
            Collection<StreamsMetadata> metadata = streams.allMetadata();
            if (metadata.size() > 0) {
                for (StreamsMetadata m: metadata) {
                    System.out.println(m);
                }
                ReadOnlyKeyValueStore<String, Long> store =
                    streams.store(storeName,
                                  QueryableStoreTypes.<String, Long>keyValueStore());
                Long num = store.get("kafka");
                System.out.println(String.format("%s -> num", "kafka", num));
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //startRestProxy(streams, applicationHost, applicationPort);
    }

    private static void startRestProxy(final KafkaStreams streams,
                                       final String applicationHost,
                                       final int applicationPort) {
        QueryableWordCountProxy service =
            new QueryableWordCountProxy(streams,
                                        applicationHost, applicationPort);
        service.start();
    }
}

