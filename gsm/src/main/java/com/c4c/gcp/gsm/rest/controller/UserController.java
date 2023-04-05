package com.c4c.gcp.gsm.rest.controller;

import com.c4c.gcp.gsm.GsmApplication;
import com.c4c.gcp.gsm.adapter.api.RestAdapterV1;
import com.c4c.gcp.gsm.rest.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.c4c.gcp.gsm.rest.controller.BaseController.BASE_URL;


@Controller
@RequestMapping(BASE_URL + "users/")
public class UserController extends BaseController{
    @Autowired
    public UserController(final RestAdapterV1 restAdapterV1) {
        super(restAdapterV1);
    }

    @GetMapping (produces = "application/json")
    public ResponseEntity<List<UserResource>> getAll() throws InterruptedException {
        //Thread.sleep(20000);
        return ResponseEntity.ok().body(this.restAdapterV1.getAllUsers());
    }

}