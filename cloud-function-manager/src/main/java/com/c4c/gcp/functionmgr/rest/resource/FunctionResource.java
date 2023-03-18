package com.c4c.gcp.functionmgr.rest.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FunctionResource {
    private String name;
    private String location;
    private String project;
}
