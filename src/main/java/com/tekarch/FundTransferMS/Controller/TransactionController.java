package com.tekarch.FundTransferMS.Controller;

import com.tekarch.FundTransferMS.Services.Interface.TransactionService;
import com.tekarch.FundTransferMS.Services.TransactionServiceImpl;
import com.tekarch.FundTransferMS.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Transactions> initiateTransfer(@RequestBody Transactions fundTransfer) {
        Transactions initiatedTransfer = transactionService.transferFunds(fundTransfer);
        return new ResponseEntity<>(initiatedTransfer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransfers() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransferById(@PathVariable Long id) {
        Optional<Transactions> transactions = transactionService.getTransactionById(id);
//        return transactions.isEmpty()
//                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//                : new ResponseEntity<>(transactions.get(), HttpStatus.OK);

        return transactions.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
