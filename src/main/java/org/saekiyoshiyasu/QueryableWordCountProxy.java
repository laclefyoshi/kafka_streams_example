package org.saekiyoshiyasu;

import org.apache.kafka.streams.KafkaStreams;

/**
 * @author ysaeki
 */
public class QueryableWordCountProxy {

    private KafkaStreams streams;
    private String applicationHost;
    private int applicationPort;
    
    public QueryableWordCountProxy(final KafkaStreams s,
                                   final String appHost,
                                   final int appPort) {
        streams = s;
        applicationHost = appHost;
        applicationPort = appPort;
    }

    public void start() {
        
    }
}

