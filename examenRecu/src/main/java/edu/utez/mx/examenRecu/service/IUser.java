package edu.utez.mx.examenRecu.service;

import edu.utez.mx.examenRecu.model.dto.UserDTO;
import edu.utez.mx.examenRecu.model.entity.UserBean;

import java.util.List;

public interface IUser {
    List<UserBean> findAll();
    UserBean findById(Integer id);
    UserBean save(UserDTO userDTO);
    UserBean update(UserDTO userDTO);
    void delete(UserBean user);
}
