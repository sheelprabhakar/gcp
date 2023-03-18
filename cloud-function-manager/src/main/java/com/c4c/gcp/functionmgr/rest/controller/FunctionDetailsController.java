package com.c4c.gcp.functionmgr.rest.controller;

import com.c4c.gcp.functionmgr.adapter.api.RestAdapterV1;
import com.c4c.gcp.functionmgr.rest.resource.FunctionResource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.c4c.gcp.functionmgr.common.Constants.BASE_PATH;
@RestController
@RequestMapping(BASE_PATH + "function")
public class FunctionDetailsController {

    private final RestAdapterV1 adapterV1;

    @Autowired
    public FunctionDetailsController(final RestAdapterV1 adapterV1) {
        this.adapterV1 = adapterV1;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<FunctionResource>> listAllFunction() throws IOException {
        List<FunctionResource> resources = this.adapterV1.listAllFunction();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
