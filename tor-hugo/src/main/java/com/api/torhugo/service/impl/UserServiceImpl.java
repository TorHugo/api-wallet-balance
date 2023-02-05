package com.api.torhugo.service.impl;

import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.domain.entity.UserModel;
import com.api.torhugo.mapper.UserMapper;
import com.api.torhugo.repository.BalanceRepository;
import com.api.torhugo.repository.UserRepository;
import com.api.torhugo.service.UserService;
import com.api.torhugo.util.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    final StopWatch sw =  new StopWatch();

    @Autowired
    private UserRepository repository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(final UserDTO dto) {

        sw.start("mapper");
        logger.info("1. Mapping the user.");
        final UserModel model = userMapper.mapper(dto);
        sw.stop();

        sw.start("save");
        logger.info("2. Saving user in database.");
        repository.save(model);
        sw.stop();

        return new UserDTO(model);
    }

    @Override
    public UserDTO findById(final Long idUser) {

        sw.start("findById");
        logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel model = repository.findById(idUser).orElseThrow();
        sw.stop();

        sw.start("findAllBalanceByWalletId");
        logger.info("2. Searching wallet by id: {} in database.", model.getWalletModel().getIdWallet());
        List<BalanceModel> lsBalance = balanceRepository.findAllBalanceByWalletId(model.getIdUser());
        model.getWalletModel().setLsBalance(lsBalance);
        sw.stop();

        return new UserDTO(model);
    }

    @Override
    public List<UserDTO> findAll() {

        sw.start("findAll");
        logger.info("1. Searching all database users.");
        List<UserModel> lsModel = repository.findAll();
        sw.stop();

        return lsModel.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(final Long idUser, final UserDTO userDTO) {

        sw.start("findByIdUser");
        logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        sw.stop();

        sw.start("mapper");
        logger.info("2. Mapping the user.");
        recuperedUser = userMapper.mapper(recuperedUser);
        sw.stop();

        sw.start("save");
        logger.info("3. Saving user in database.");
        recuperedUser = repository.save(recuperedUser);
        sw.stop();

        return new UserDTO(recuperedUser);
    }

    @Override
    public void delete(final Long idUser) {

        sw.start("findByIdUser");
        logger.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        sw.stop();

        sw.start("delete");
        logger.info("2. Deleting user by id: {}.", idUser);
        repository.delete(recuperedUser);
        sw.stop();
    }

    private UserModel findByIdUser(final Long idUser){
        return repository.findById(idUser).orElseThrow();
    }

}
