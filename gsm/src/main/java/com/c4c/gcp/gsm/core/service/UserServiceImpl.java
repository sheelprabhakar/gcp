package com.c4c.gcp.gsm.core.service;

import com.c4c.gcp.gsm.core.repository.db.UsersRepository;
import com.c4c.gcp.gsm.core.repository.db.dao.UserDO;
import com.c4c.gcp.gsm.core.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDO> getAllUsers() {
        return this.usersRepository.findAll();
    }
}
