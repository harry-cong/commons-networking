package com.cisco.commons.networking;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SmeeIOClientTest {


    @Test
    public void sseClientTestForSmee(){
      log.info("Test@@");
        List<String> events = new LinkedList<>();
        String url = "https://smee.io/KaRbQgEVGNXfgHPG";
        EventHandler eventHandler = eventText -> { events.add(eventText); log.info("Event Received:{}",eventText);};
        SSEClient sseClient = SSEClient.builder().url(url).eventHandler(eventHandler)
                .reconnectSamplingTimeMillis(1000L)
                .useKeepAliveMechanismIfReceived(true)
                .connectivityCheckIntervalSeconds(1).minConnectivityThresholdSeconds(1)
                .build();
        log.info("Starting SSE Client");
        sseClient.start();
        sleepQuitely(10000);

        SSEClient.SubscribeStatus status = sseClient.getStatus();

        log.info("status:{}",status.name());

    }


    private void sleepQuitely(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("Error sleeping: " + e.getMessage());
        }
    }
}
