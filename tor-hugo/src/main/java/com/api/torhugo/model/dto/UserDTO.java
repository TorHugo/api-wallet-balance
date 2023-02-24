package com.api.torhugo.model.dto;

import com.api.torhugo.model.entity.UserModel;
import com.api.torhugo.model.entity.WalletModel;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(UserModel userModel){
        this.idUser = userModel.getIdUser();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.phone = userModel.getPhone();
        this.walletModel = userModel.getWalletModel();
        userModel.getRoles().forEach(role -> {
            this.roles.add(new RoleDTO(role));
        });
    }
}
