package com.api.torhugo.mapper;

import com.api.torhugo.domain.dto.BalanceDTO;
import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper {
    @Autowired
    private ApplicationUtils utils;

    public BalanceModel mapper(final BalanceDTO dto){
        BalanceModel model = new BalanceModel();
        model.setIdBalance(utils.convertToLong(dto.getIdBalance()));
        model.setDescription(utils.isStringNull(dto.getDescription()));
        model.setBalanceValue(dto.getBalanceValue());
        model.setAdditionDate(utils.validationExistingDate(dto.getAdditionDate()));
        model.setIdWallet(utils.convertToLong(dto.getIdWallet()));
        model.setTypeBalance(dto.getTypeBalance());

        return model;
    }
}
