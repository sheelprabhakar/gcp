package com.c4c.gcp.cloudfunction;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.cloud.functions.v2.Function;
import com.google.cloud.functions.v2.FunctionName;
import com.google.cloud.functions.v2.FunctionServiceClient;
import com.google.cloud.functions.v2.LocationName;
import com.google.gson.Gson;
import org.apache.tools.ant.util.StringUtils;
import org.codehaus.groovy.util.StringUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunctionManager implements HttpFunction {
    // Simple function to return List of function
    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws IOException {
        List<String> names = this.getFunctions();
        Gson gson = new Gson();
        String functionNames = gson.toJson(names);

        BufferedWriter writer = response.getWriter();
        writer.write(functionNames);
    }

    private List<String> getFunctions() throws IOException {
        List<String> result = new ArrayList<>();
        try (FunctionServiceClient functionServiceClient = FunctionServiceClient.create()) {
            String location = System.getenv("location");
            if(isEmpty(location)){
                location = "us-central1"; //Default
            }

            String project = System.getenv("project");
            if(isEmpty(project)){
                project = "c4c-test-336714"; //Default
            }
            LocationName locationName = LocationName.newBuilder().setLocation(location).setProject(project).build();
            FunctionServiceClient.ListFunctionsPagedResponse response = functionServiceClient.listFunctions(locationName);
            for (FunctionServiceClient.ListFunctionsPage page : response.iteratePages()) {
                for (Function function : page.iterateAll()) {
                    result.add( function.getName());
                }

            }
            return result;
        }
    }

    static boolean isEmpty(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }
        return false;
    }
}