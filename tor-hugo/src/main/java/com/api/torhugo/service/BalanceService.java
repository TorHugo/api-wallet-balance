package com.api.torhugo.service;

import com.api.torhugo.model.dto.BalanceDTO;
import com.api.torhugo.model.dto.DeleteBalanceDTO;
import com.api.torhugo.model.dto.LsBalanceDTO;
import com.api.torhugo.model.dto.UserDTO;
import com.api.torhugo.model.enums.TypeBalance;

import java.util.List;

public interface BalanceService {
    BalanceDTO createdBalance(BalanceDTO balance, Long idUser);
    List<BalanceDTO> createdLsBalance(LsBalanceDTO lsBalance);
    List<BalanceDTO> findAll(Long idWallet);
    UserDTO findAllMoviment(Long idWallet, TypeBalance typeBalance);

    BalanceDTO updateBalance(Long idBalance, BalanceDTO balanceDTO);

    void delete(Long idBalance);
    void delete(DeleteBalanceDTO deleteBalanceDTO);
}
