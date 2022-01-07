package com.superzyen.zyengame.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    public static final String DEFUALT_DIR_NAME = "account";
    public static final String DEFUALT_FILE_NAME = "account.txt";
    private String localId;
    private String userName;
    private BigDecimal coin;
}
