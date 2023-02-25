package com.api.torhugo.service.impl;

import com.api.torhugo.model.dto.UserDTO;
import com.api.torhugo.model.entity.BalanceModel;
import com.api.torhugo.model.entity.UserModel;
import com.api.torhugo.exception.impl.DataBaseException;
import com.api.torhugo.mapper.UserMapper;
import com.api.torhugo.repository.BalanceRepository;
import com.api.torhugo.repository.UserRepository;
import com.api.torhugo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    final StopWatch sw =  new StopWatch();

    @Autowired
    private UserRepository repository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO createUser(final UserDTO dto) {

        sw.start("mapper");
        log.info("1. Mapping the user.");
        final UserModel model = userMapper.mapper(dto);
        passwordEnconder(model, model.getPassword());
        sw.stop();

        sw.start("save");
        log.info("2. Saving user in database.");
        repository.save(model);
        sw.stop();

        return new UserDTO(model);
    }

    @Override
    public UserDTO findById(final Long idUser) {

        sw.start("findById");
        log.info("1. Searching user by id: {} in database.", idUser);
        UserModel model = repository.findById(idUser).orElseThrow(()-> new DataBaseException("Entity not found."));
        sw.stop();

        sw.start("findAllBalanceByWalletId");
        log.info("2. Searching wallet by id: {} in database.", model.getWalletModel().getIdWallet());
        List<BalanceModel> lsBalance = balanceRepository.findAllBalanceByWalletId(model.getIdUser());
        model.getWalletModel().setLsBalance(lsBalance);
        sw.stop();

        return new UserDTO(model);
    }

    @Override
    public List<UserDTO> findAll() {

        sw.start("findAll");
        log.info("1. Searching all database users.");
        List<UserModel> lsModel = repository.findAll();
        sw.stop();

        return lsModel.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(final Long idUser, final UserDTO userDTO) {

        sw.start("findByIdUser");
        log.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        sw.stop();

        sw.start("mapper");
        log.info("2. Mapping the user.");
        recuperedUser = userMapper.mapper(recuperedUser);
        sw.stop();

        sw.start("save");
        log.info("3. Saving user in database.");
        recuperedUser = repository.save(recuperedUser);
        sw.stop();

        return new UserDTO(recuperedUser);
    }

    @Override
    public void delete(final Long idUser) {

        sw.start("findByIdUser");
        log.info("1. Searching user by id: {} in database.", idUser);
        UserModel recuperedUser = findByIdUser(idUser);
        sw.stop();

        sw.start("delete");
        log.info("2. Deleting user by id: {}.", idUser);
        repository.delete(recuperedUser);
        sw.stop();
    }

    private UserModel findByIdUser(final Long idUser){
        return repository.findById(idUser).orElseThrow(()-> new DataBaseException("Entity not found."));
    }

    private void passwordEnconder(final UserModel entity, final String password){
        entity.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("1. Searching user in the database, by user email {}", email);
        UserModel user = repository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("Email not found.");

        return user;
    }

}
