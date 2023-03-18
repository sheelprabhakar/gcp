package com.c4c.gcp.functionmgr.core.service.api;

import com.google.cloud.functions.v2.FunctionName;

import java.io.IOException;
import java.util.List;

public interface FunctionDetailsService {
    List<FunctionName> listAllFunction() throws IOException;
}
