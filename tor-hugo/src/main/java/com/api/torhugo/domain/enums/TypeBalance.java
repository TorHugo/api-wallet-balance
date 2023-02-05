package com.api.torhugo.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TypeBalance {

    DEPOSIT(1L),
    OUT(2L);

    private Long idType;

    public static TypeBalance parse(final Long idType){
        return Arrays.stream(values()).filter(valeu -> valeu.idType.equals(idType)).findFirst().orElseThrow();
    }
}
