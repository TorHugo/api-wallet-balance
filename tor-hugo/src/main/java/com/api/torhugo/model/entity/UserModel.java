package com.api.torhugo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String email;
    private String password;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_wallet")
    private WalletModel walletModel;

    @ManyToMany(fetch = FetchType.EAGER) // força que sempre que trazer o userModel, trará o role também;
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") // referencia a fk da tabela da associação
    )
    private Set<RoleModel> roles = new HashSet<>();
}
