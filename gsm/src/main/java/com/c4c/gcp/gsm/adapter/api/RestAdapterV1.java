package com.c4c.gcp.gsm.adapter.api;

import com.c4c.gcp.gsm.rest.resource.UserResource;

import java.util.List;

public interface RestAdapterV1 {
    List<UserResource> getAllUsers();
}