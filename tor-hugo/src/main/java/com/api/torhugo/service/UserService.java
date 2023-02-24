package com.api.torhugo.service;

import com.api.torhugo.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO dto);
    UserDTO findById(Long idUser);

    List<UserDTO> findAll();

    UserDTO updateUser(Long idUser, UserDTO userDTO);

    void delete(Long idUser);
}
