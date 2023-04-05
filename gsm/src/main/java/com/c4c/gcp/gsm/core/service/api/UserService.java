package com.c4c.gcp.gsm.core.service.api;

import com.c4c.gcp.gsm.core.repository.db.dao.UserDO;

import java.util.List;

public interface UserService {
    List<UserDO> getAllUsers();
}