package com.c4c.gcp.functionmgr.adapter.api;

import com.c4c.gcp.functionmgr.rest.resource.FunctionResource;

import java.io.IOException;
import java.util.List;

public interface RestAdapterV1 {
    List<FunctionResource> listAllFunction() throws IOException;
}
