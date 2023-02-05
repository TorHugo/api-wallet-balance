package com.api.torhugo.service.impl;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.mapper.BalanceMapper;
import com.api.torhugo.repository.BalanceRepository;
import com.api.torhugo.service.BalanceService;
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
public class BalanceServiceImpl implements BalanceService {

    public Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);
    final StopWatch sw =  new StopWatch();

    @Autowired
    private BalanceRepository repository;

    @Autowired
    private ApplicationUtils utils;

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public BalanceDTO createdBalance(final BalanceDTO balance) {

        sw.start("mapper");
        logger.info("1. Mapping the balance.");
        BalanceModel model = balanceMapper.mapper(balance);
        sw.stop();

        sw.start("save");
        logger.info("2. Saving balance in database.");
        repository.save(model);
        sw.stop();

        return new BalanceDTO(model);
    }

    @Override
    public List<BalanceDTO> findAll(final Long idWallet) {

        sw.start("findAllBalanceByWalletId");
        logger.info("1. Searching all database users.");
        List<BalanceModel> lsModel = repository.findAllBalanceByWalletId(idWallet);
        sw.stop();

        return lsModel.stream().map(BalanceDTO::new).collect(Collectors.toList());
    }

    private BalanceModel findByIdBalance(final Long idBalance){
        return repository.findById(idBalance).orElse(null);
    }
}
