package com.tekarch.FundTransferMS.Services.Interface;

import com.tekarch.FundTransferMS.model.Transactions;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transactions transferFunds(Transactions fundTransfer);
    List<Transactions> getAllTransactions();
    Optional<Transactions> getTransactionById(Long transactionId);
}
