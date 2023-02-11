package com.api.torhugo.service.impl;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.dto.DeleteBalanceDTO;
import com.api.torhugo.domain.dto.LsBalanceDTO;
import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.domain.entity.UserModel;
import com.api.torhugo.domain.enums.TypeBalance;
import com.api.torhugo.exception.impl.DataBaseException;
import com.api.torhugo.mapper.BalanceMapper;
import com.api.torhugo.repository.BalanceRepository;
import com.api.torhugo.repository.UserRepository;
import com.api.torhugo.service.BalanceService;
import com.api.torhugo.util.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    final StopWatch sw =  new StopWatch();

    @Autowired
    private BalanceRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationUtils utils;

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public BalanceDTO createdBalance(final BalanceDTO balance, final Long idUser) {
        log.info("1. Validating exists user wallet in the database for idUser {}.", idUser);
        UserModel user = findByIdUser(idUser);

        log.info("2. Mapping the balance.");
        BalanceModel model = balanceMapper.mapper(balance);

        log.info("3. Saving balance in database.");
        repository.save(model);

        return new BalanceDTO(model);
    }

    @Override
    public List<BalanceDTO> createdLsBalance(final LsBalanceDTO lsBalanceDTO) {
        log.info("1. Validating exists user wallet in the database for idUser {}.", lsBalanceDTO.getIdUser());
        UserModel user = findByIdUser(lsBalanceDTO.getIdUser());

        lsBalanceDTO.getLsBalance().forEach(balance -> {
            log.info("2. Mapping the balance.");
            BalanceModel model = balanceMapper.mapper(balance);

            log.info("3. Saving balance in database.");
            repository.save(model);
        });

        return lsBalanceDTO.getLsBalance().stream().map(BalanceDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<BalanceDTO> findAll(final Long idWallet) {
        log.info("1. Searching wallet in the database for idWallet {}.", idWallet);
        List<BalanceModel> lsModel = repository.findAllBalanceByWalletId(idWallet);

        return lsModel.stream().map(BalanceDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO findAllMoviment(final Long idWallet, final TypeBalance typeBalance) {
        if (typeBalance.equals(TypeBalance.PAYIN)){
            log.info("1. Searching user by id: {} in database.", idWallet);
            UserModel model = findByIdUser(idWallet);

            log.info("1. Searching all deposit in the database, for wallet {}.", idWallet);
            List<BalanceModel> lsBalance = repository.findAllBalanceByDeposit();
            model.getWalletModel().setLsBalance(lsBalance);

            return new UserDTO(model);
        } else {
            log.info("1. Searching user by id: {} in database.", idWallet);
            UserModel model = findByIdUser(idWallet);

            log.info("1. Searching all outflow in the database, for wallet {}.", idWallet);
            List<BalanceModel> lsBalance = repository.findAllBalanceByOutflow();
            model.getWalletModel().setLsBalance(lsBalance);

            return new UserDTO(model);
        }
    }

    @Override
    public BalanceDTO updateBalance(Long idBalance, BalanceDTO balanceDTO) {

        log.info("1. Searching balance by id: {} in database.", idBalance);
        BalanceModel recuperedBalance = findByIdBalance(idBalance);

        log.info("2. Mapping the balance.");
        recuperedBalance = balanceMapper.mapper(recuperedBalance);

        sw.start("save");
        log.info("3. Saving user in database.");
        recuperedBalance = repository.save(recuperedBalance);
        sw.stop();

        return new BalanceDTO(recuperedBalance);
    }

    @Override
    public void delete(Long idBalance) {
        log.info("1. Searching balance by id: {} in database.", idBalance);
        BalanceModel recuperedBalance = findByIdBalance(idBalance);

        log.info("2. Deleting balance by id: {}.", idBalance);
        repository.delete(recuperedBalance);
    }

    @Override
    public void delete(final DeleteBalanceDTO deleteBalanceDTO) {

        log.info("1. Validating exists wallet in the database for idWallet {}.", deleteBalanceDTO.getIdWallet());
        UserModel user = findByIdUser(deleteBalanceDTO.getIdWallet());

        deleteBalanceDTO.getLsIdBalance().forEach(idBalance -> {
            log.info("2. Searching balance by id: {} in database.", idBalance);
            BalanceModel recuperedBalance = findByIdBalance(idBalance);

            log.info("3. Deleting balance by id: {}.", idBalance);
            repository.delete(recuperedBalance);
        });
    }

    private UserModel findByIdUser(final Long idUser){
        return userRepository.findById(idUser).orElseThrow(()-> new DataBaseException("Entity not found."));
    }

    private BalanceModel findByIdBalance(final Long idBalance){
        return repository.findById(idBalance).orElseThrow(()-> new DataBaseException("Entity not found."));
    }
}
