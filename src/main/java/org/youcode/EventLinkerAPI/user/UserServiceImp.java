package org.youcode.EventLinkerAPI.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;


@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;

    @Override
    public User getUserEntityById(Long id){
        return userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with given ID !"));
    }
}