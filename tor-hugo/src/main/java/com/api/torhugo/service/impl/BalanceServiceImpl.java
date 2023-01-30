package com.api.torhugo.service.impl;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.domain.entity.UserModel;
import com.api.torhugo.mapper.BalanceMapper;
import com.api.torhugo.repository.BalanceRepository;
import com.api.torhugo.service.BalanceService;
import com.api.torhugo.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository repository;

    @Autowired
    private ApplicationUtils utils;

    @Autowired
    private BalanceMapper mapper;

    @Override
    public List<BalanceDTO> createdBalance(final List<BalanceDTO> lsBalance) {
        ApplicationUtils.logger.info("1.1 - Validantion existing balance in database.");
        lsBalance.forEach(balanceDTO -> {
//            if (validationExistingBalance(balanceDTO.getIdBalance()).equals(Boolean.TRUE)){
//
//            }
        });

        return null;
    }

    private Boolean validationExistingBalance(final Long idBalance){
        Boolean existing;
        final BalanceModel recuperedBalance = findByIdBalance(idBalance);

        if (recuperedBalance == null){
            existing = Boolean.FALSE;
        } else
            existing = Boolean.TRUE;

        return existing;
    }

    private BalanceModel findByIdBalance(final Long idBalance){
        return repository.findById(idBalance).orElse(null);
    }
}
