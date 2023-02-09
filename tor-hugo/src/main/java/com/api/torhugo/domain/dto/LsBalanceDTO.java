package com.api.torhugo.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LsBalanceDTO {
    private Long idUser;
    private List<BalanceDTO> lsBalance;
}
