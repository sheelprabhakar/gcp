package com.c4c.gcp.gsm.core.service.api;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessageChannel {

    String MESSAGES="messages";

    @Input
    SubscribableChannel messages();
}