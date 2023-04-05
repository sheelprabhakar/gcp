package com.c4c.gcp.gsm.rest.controller;

import com.c4c.gcp.gsm.adapter.api.RestAdapterV1;
import com.c4c.gcp.gsm.core.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.c4c.gcp.gsm.rest.controller.BaseController.BASE_URL;

@Controller
@RequestMapping(BASE_URL + "refresh/")
@EnableBinding(Source.class)
public class RefreshController extends BaseController{

    private final Source source;
    @Autowired
    public RefreshController(final RestAdapterV1 restAdapterV1,
                             final Source source) {
        super(restAdapterV1);
        this.source = source;
    }


    @PostMapping( produces = "application/json")
    public ResponseEntity<Void> refresh() throws InterruptedException {
        Message message = new Message("Hello World from Publisher");

        source.output().send(MessageBuilder.withPayload(message).build());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
