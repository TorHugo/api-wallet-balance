package com.api.torhugo.domain.dto;

import com.api.torhugo.domain.entity.BalanceModel;
import com.api.torhugo.domain.entity.UserModel;
import lombok.*;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BalanceDTO {
    private Long idBalance;
    private String description;
    private BigDecimal balanceValue;
    private LocalDate additionDate;
    private Long idWallet;

    public BalanceDTO(BalanceModel balanceModel){
        this.idBalance = balanceModel.getIdBalance();
        this.description = balanceModel.getDescription();
        this.balanceValue = balanceModel.getBalanceValue();
        this.additionDate = balanceModel.getAdditionDate();
        this.idWallet = balanceModel.getIdWallet();
    }
}
