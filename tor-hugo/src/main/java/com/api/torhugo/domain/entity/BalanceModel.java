package com.api.torhugo.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_balance")
public class BalanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_balance")
    private Long idBalance;
    @Column(name="description")
    private String description;
    @Column(name="balance_value")
    private BigDecimal balanceValue;
    @Column(name="addition_date")
    private LocalDate additionDate;
    @Column(name = "id_wallet")
    private Long idWallet;
}
