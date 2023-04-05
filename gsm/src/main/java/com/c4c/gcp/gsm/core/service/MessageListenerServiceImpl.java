package com.c4c.gcp.gsm.core.service;

import com.c4c.gcp.gsm.core.dto.Message;
import com.c4c.gcp.gsm.core.service.api.MessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(MessageChannel.class)
public class MessageListenerServiceImpl {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public MessageListenerServiceImpl(final ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }
    @StreamListener(MessageChannel.MESSAGES)
    public void handleMessage(Message message){
        System.out.println("Subscriber Received Message is: " + message);
        //GsmApplication.restart();
        this.eventPublisher.publishEvent(new RefreshEvent(this, "RefreshEvent", "Refreshing scope"));
    }
}
