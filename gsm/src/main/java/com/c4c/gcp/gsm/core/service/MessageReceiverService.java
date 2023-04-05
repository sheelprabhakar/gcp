package com.c4c.gcp.gsm.core.service;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.SubscriptionName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MessageReceiverService implements MessageReceiver {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageReceiverService.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
        System.out.println(message.getMessageId().toString());
        System.out.println(message.getData().toStringUtf8());
        consumer.ack();
       // this.eventPublisher.publishEvent(new RefreshEvent(this, "RefreshEvent", "Refreshing scope"));
    }


    private final SubscriptionName subscription = SubscriptionName.parse("projects/c4c-test-336714/subscriptions/dual-user-poc-db-cred-changes-sub");

    @PostConstruct
    private void init(){
        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscription.toString(), this).build();
            subscriber.addListener(
                    new Subscriber.Listener() {
                        @Override
                        public void failed(Subscriber.State from, Throwable failure) {
                            // Handle failure. This is called when the Subscriber encountered a fatal error and is shutting down.
                            LOGGER.error(failure.getMessage());
                        }
                    },
                    MoreExecutors.directExecutor());
            subscriber.startAsync().awaitRunning();
            System.out.println("exited");
        } finally {
            if (subscriber != null) {
                subscriber.stopAsync();
            }
        }
    }
}
