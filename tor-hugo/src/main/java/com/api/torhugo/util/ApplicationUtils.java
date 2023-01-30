package com.api.torhugo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ApplicationUtils {

    public static Logger logger = LoggerFactory.getLogger(Class.class);

    public String isStringNull(final String atribute){
        return isNull(atribute).equals(Boolean.FALSE) ? atribute : "Atribute is null.";
    }
    public Boolean isNull(final String atribute){
        return atribute == null ? Boolean.TRUE : Boolean.FALSE;
    }
    public Long convertToLong(final Long atribute){
        if (atribute == null)
            return null;
        else
            return Long.valueOf(isStringNull(String.valueOf(atribute)));
    }
    public BigDecimal convertToBigDecimal(final BigDecimal atribute){
        if (atribute == null)
            return null;
        else
            return BigDecimal.valueOf(Long.parseLong(isStringNull(String.valueOf(atribute))));
    }
    public LocalDate validationExistingDate(final LocalDate atribute){
        if (atribute == null)
            return LocalDate.now();
        else
            return atribute;
    }
}
