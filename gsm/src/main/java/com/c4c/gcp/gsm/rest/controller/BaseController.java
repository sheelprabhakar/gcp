package com.c4c.gcp.gsm.rest.controller;

import com.c4c.gcp.gsm.adapter.api.RestAdapterV1;
import org.springframework.web.bind.annotation.RestController;

public class BaseController {
    final RestAdapterV1 restAdapterV1;
    final static String BASE_URL ="/api/v1/";

    public BaseController(final RestAdapterV1 restAdapterV1) {

        this.restAdapterV1 = restAdapterV1;
    }
}
