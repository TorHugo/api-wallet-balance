package com.api.torhugo.domain.dto;

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
