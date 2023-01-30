package com.api.torhugo.domain.dto;

import com.api.torhugo.domain.entity.UserModel;
import com.api.torhugo.domain.entity.WalletModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class UserDTO {

    private Long idUser;
    private String name;
    private String email;
    private String password;
    private String phone;
    private WalletModel walletModel;

    public UserDTO(UserModel userModel){
        this.idUser = userModel.getIdUser();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.phone = userModel.getPhone();
        this.walletModel = userModel.getWalletModel();
    }
}
