package com.api.torhugo.service;

import com.api.torhugo.domain.dto.BalanceDTO;
import java.util.List;

public interface BalanceService {
    BalanceDTO createdBalance(BalanceDTO balance);
    List<BalanceDTO> findAll(Long idWallet);
}
