
# メモ #

## WordCount ##

```
$ ./bin/kafka-topics.sh --create --topic input-stream \
    --replication-factor 1 --partitions 1 --zookeeper 127.0.0.1:2181
$ ./bin/kafka-topics.sh --create --topic output-stream \
    --replication-factor 1 --partitions 1 --zookeeper 127.0.0.1:2181
$ ./bin/kafka-topics.sh --list --zookeeper 127.0.0.1:2181

$ java -cp target/kafka_streams_example-1.0-SNAPSHOT.jar \
    org.saekiyoshiyasu.App

$ ./bin/kafka-console-producer.sh --topic input-stream \
    --broker-list 127.0.0.1:9092
Hello Kafka streams
All streams lead to Kafka
Join kafka summit

$ ./bin/kafka-console-consumer.sh --topic input-stream \
    --from-beginning \
    --bootstrap-server 127.0.0.1:9092
$ ./bin/kafka-console-consumer.sh --topic output-stream \
    --from-beginning \
    --bootstrap-server 127.0.0.1:9092 \
    --formatter kafka.tools.DefaultMessageFormatter \
    --property print.key=true \
    --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
    --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

```
$ open http://127.0.0.1:18080/results/kafka
```

