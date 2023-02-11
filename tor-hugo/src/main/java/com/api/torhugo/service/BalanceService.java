package com.api.torhugo.service;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.dto.DeleteBalanceDTO;
import com.api.torhugo.domain.dto.LsBalanceDTO;
import com.api.torhugo.domain.dto.UserDTO;
import com.api.torhugo.domain.enums.TypeBalance;

import java.time.LocalDate;
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
