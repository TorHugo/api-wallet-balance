package com.api.torhugo.service;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.enums.TypeBalance;

import java.util.List;

public interface BalanceService {
    BalanceDTO createdBalance(BalanceDTO balance, Long idUser);
    List<BalanceDTO> findAll(Long idWallet);
    UserDTO findAllMoviment(Long idWallet, TypeBalance typeBalance);
}
