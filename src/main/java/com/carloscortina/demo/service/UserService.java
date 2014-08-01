package com.carloscortina.demo.service;

import com.carloscortina.demo.model.User;
import java.util.List;

public interface UserService extends GenericService<User>{

    public User getUserByUsername(String username);
    public List<User> getUserByRole(int idRole);
}
