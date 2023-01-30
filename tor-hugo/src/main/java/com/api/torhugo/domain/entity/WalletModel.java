package com.api.torhugo.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_wallet")
public class WalletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Long idWallet;
    @OneToMany
    private Set<BalanceModel> lsBalance = new HashSet<>();
}
