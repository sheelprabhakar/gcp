package com.c4c.gcp.gsm.adapter.impl;

import com.c4c.gcp.gsm.adapter.api.RestAdapterV1;
import com.c4c.gcp.gsm.core.repository.db.dao.UserDO;
import com.c4c.gcp.gsm.core.service.api.UserService;
import com.c4c.gcp.gsm.rest.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestAdapterV1Impl implements RestAdapterV1 {

    private final UserService userService;
    @Autowired
    public RestAdapterV1Impl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserResource> getAllUsers() {
        List<UserResource> userResources = new ArrayList<>();
        List<UserDO> userDOList = this.userService.getAllUsers();
        userDOList.forEach(userDO -> {
            userResources.add(new UserResource(userDO.getId(), userDO.getName(), userDO.getEmail(), userDO.isEnabled(), null));
        });
        return userResources;
    }
}
