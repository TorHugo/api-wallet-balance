package com.api.torhugo.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteBalanceDTO {
    private Long idWallet;
    private List<Long> lsIdBalance;
}
