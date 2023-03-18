package com.c4c.gcp.functionmgr.adapter.impl;

import com.c4c.gcp.functionmgr.adapter.api.RestAdapterV1;
import com.c4c.gcp.functionmgr.core.service.api.FunctionDetailsService;
import com.c4c.gcp.functionmgr.rest.resource.FunctionResource;
import com.google.cloud.functions.v2.FunctionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class RestAdapterV1Impl implements RestAdapterV1 {

    private final FunctionDetailsService functionDetailsService;

    @Autowired
    public RestAdapterV1Impl(final FunctionDetailsService functionDetailsService) {
        this.functionDetailsService = functionDetailsService;
    }

    @Override
    public List<FunctionResource> listAllFunction() throws IOException {
        List<FunctionName> functionNames = this.functionDetailsService.listAllFunction();
        List<FunctionResource> functionResources = new ArrayList<>();
        for (FunctionName name : functionNames) {
            functionResources.add(this.convertFromFunctionName2FunctionResource(name));
        }
        return functionResources;
    }

    private FunctionResource convertFromFunctionName2FunctionResource(FunctionName name){
        return FunctionResource.builder().project(name.getProject()).name(name.getFunction())
                .location(name.getLocation()).build();
    }
}
