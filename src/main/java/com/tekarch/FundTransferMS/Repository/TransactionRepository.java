package com.tekarch.FundTransferMS.Repository;

import com.tekarch.FundTransferMS.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
