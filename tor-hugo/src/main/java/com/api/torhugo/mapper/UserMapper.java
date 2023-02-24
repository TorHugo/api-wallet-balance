package com.api.torhugo.mapper;

import com.api.torhugo.model.dto.UserDTO;
import com.api.torhugo.model.entity.UserModel;
import com.api.torhugo.model.entity.WalletModel;
import com.api.torhugo.util.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ApplicationUtils utils;

    public UserModel mapper (final UserDTO dto){
        UserModel model = new UserModel();
        model.setIdUser(utils.convertToLong(dto.getIdUser()));
        model.setName(utils.isStringNull(dto.getName()));
        model.setEmail(utils.isStringNull(dto.getEmail()));
        model.setPassword(utils.isStringNull(dto.getPassword()));
        model.setPhone(utils.isStringNull(dto.getPhone()));
        model.setWalletModel(validationWallet(dto.getWalletModel()));

        return model;
    }

    public UserModel mapper (final UserModel dto){
        UserModel model = new UserModel();
        model.setIdUser(utils.convertToLong(dto.getIdUser()));
        model.setName(utils.isStringNull(dto.getName()));
        model.setEmail(utils.isStringNull(dto.getEmail()));
        model.setPassword(utils.isStringNull(dto.getPassword()));
        model.setPhone(utils.isStringNull(dto.getPhone()));
        model.setWalletModel(validationWallet(dto.getWalletModel()));

        return model;
    }

    private WalletModel validationWallet(final WalletModel walletModel){
        if(walletModel == null){
            final WalletModel model = new WalletModel();
            model.setLsBalance(null);
            return new WalletModel();
        }
        else
            return walletModel;
    }

}
