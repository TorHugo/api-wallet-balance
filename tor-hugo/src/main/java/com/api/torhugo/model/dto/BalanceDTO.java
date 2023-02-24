package com.api.torhugo.model.dto;

import com.api.torhugo.model.entity.BalanceModel;
import com.api.torhugo.model.enums.TypeBalance;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    private TypeBalance typeBalance;

    public BalanceDTO(BalanceModel balanceModel){
        this.idBalance = balanceModel.getIdBalance();
        this.description = balanceModel.getDescription();
        this.balanceValue = balanceModel.getBalanceValue();
        this.additionDate = balanceModel.getAdditionDate();
        this.idWallet = balanceModel.getIdWallet();
        this.typeBalance = balanceModel.getTypeBalance();
    }

    public BalanceDTO(List<BalanceDTO> lsBalanceModel){
        lsBalanceModel.forEach(balanceModel -> {
            this.idBalance = balanceModel.getIdBalance();
            this.description = balanceModel.getDescription();
            this.balanceValue = balanceModel.getBalanceValue();
            this.additionDate = balanceModel.getAdditionDate();
            this.idWallet = balanceModel.getIdWallet();
            this.typeBalance = balanceModel.getTypeBalance();
        });
    }

    public BalanceDTO(BalanceDTO balanceDTO) {
        this.idBalance = balanceDTO.getIdBalance();
        this.description = balanceDTO.getDescription();
        this.balanceValue = balanceDTO.getBalanceValue();
        this.additionDate = balanceDTO.getAdditionDate();
        this.idWallet = balanceDTO.getIdWallet();
        this.typeBalance = balanceDTO.getTypeBalance();
    }
}
