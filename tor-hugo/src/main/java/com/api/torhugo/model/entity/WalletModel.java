package com.api.torhugo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_wallet")
public class WalletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Long idWallet;
    @OneToMany
    private List<BalanceModel> lsBalance = new ArrayList<>();
}
