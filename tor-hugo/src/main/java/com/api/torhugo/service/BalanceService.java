package com.api.torhugo.service;

import com.api.torhugo.domain.dto.BalanceDTO;

import java.util.List;

public interface BalanceService {
    List<BalanceDTO> createdBalance(List<BalanceDTO> lsBalance);
}
