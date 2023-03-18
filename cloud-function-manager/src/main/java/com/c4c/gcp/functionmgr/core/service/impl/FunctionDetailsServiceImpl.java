package com.c4c.gcp.functionmgr.core.service.impl;

import com.c4c.gcp.functionmgr.common.Utils;
import com.c4c.gcp.functionmgr.core.service.api.FunctionDetailsService;
import com.google.cloud.functions.v2.Function;
import com.google.cloud.functions.v2.FunctionName;
import com.google.cloud.functions.v2.FunctionServiceClient;
import com.google.cloud.functions.v2.LocationName;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.c4c.gcp.functionmgr.common.Constants.*;

@Service
public class FunctionDetailsServiceImpl implements FunctionDetailsService {

    @Override
    public List<FunctionName> listAllFunction() throws IOException {
        List<FunctionName> functionNames = new ArrayList<>();
        List<Function> functions = this.listFunctions();
        for (Function fn : functions){
            String parts[] = fn.getName().split("/");
            if(parts.length == 6) {
                functionNames.add(FunctionName.newBuilder().setFunction(parts[5]).setLocation(parts[3])
                        .setProject(parts[1]).build());
            }
        }
        return functionNames;
    }
    private List<Function> listFunctions() throws IOException {
        List<Function> result = new ArrayList<>();
        try (FunctionServiceClient functionServiceClient = FunctionServiceClient.create()) {
            String location = System.getenv(LOCATION);
            if(Utils.isEmpty(location)){
                location = "us-central1"; //Default
            }

            String project = System.getenv(PROJECT);
            if(Utils.isEmpty(project)){
                project = "c4c-test-336714"; //Default
            }
            LocationName locationName = LocationName.newBuilder().setLocation(location).setProject(project).build();
            FunctionServiceClient.ListFunctionsPagedResponse response = functionServiceClient.listFunctions(locationName);
            for (FunctionServiceClient.ListFunctionsPage page : response.iteratePages()) {
                for (Function function : page.iterateAll()) {
                    result.add( function);
                }
            }
            return result;
        }
    }

}