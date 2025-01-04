package com.tekarch.FundTransferMS.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountsDTO {

    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Double balance;
}
