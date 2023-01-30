package com.api.torhugo.service.impl;

import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.entity.UserModel;
import com.api.torhugo.mapper.UserMapper;
import com.api.torhugo.repository.UserRepository;
import com.api.torhugo.service.UserService;
import com.api.torhugo.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(final UserDTO dto) {
        ApplicationUtils.logger.info("1. Mapping the user.");
        final UserModel model = userMapper.mapper(dto);
        ApplicationUtils.logger.info("2. Saving user in database.");
        repository.save(model);

        return new UserDTO(model);
    }

    @Override
    public UserDTO findById(final Long idUser) {
        ApplicationUtils.logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel model = repository.findById(idUser).orElseThrow();
        return new UserDTO(model);
    }

    @Override
    public List<UserDTO> findAll() {
        ApplicationUtils.logger.info("1. Searching all database users.");
        List<UserModel> lsModel = repository.findAll();
        return lsModel.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(final Long idUser, final UserDTO userDTO) {
        ApplicationUtils.logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        ApplicationUtils.logger.info("2. Mapping the user.");
        recuperedUser = userMapper.mapper(recuperedUser);
        ApplicationUtils.logger.info("3. Saving user in database.");
        recuperedUser = repository.save(recuperedUser);

        return new UserDTO(recuperedUser);
    }

    @Override
    public void delete(final Long idUser) {
        ApplicationUtils.logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        ApplicationUtils.logger.info("2. Deleting user by id: {}.", idUser);
        repository.delete(recuperedUser);
    }

    private UserModel findByIdUser(final Long idUser){
        return repository.findById(idUser).orElseThrow();
    }

}
