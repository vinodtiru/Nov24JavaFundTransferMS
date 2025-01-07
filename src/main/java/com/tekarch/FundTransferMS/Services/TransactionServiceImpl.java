package com.tekarch.FundTransferMS.Services;

import com.tekarch.FundTransferMS.Repository.TransactionRepository;
import com.tekarch.FundTransferMS.Services.Interface.TransactionService;
import com.tekarch.FundTransferMS.model.AccountsDTO;
import com.tekarch.FundTransferMS.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${accounts.ms.url}")
    String url;

    @Override
    public Transactions transferFunds(Transactions fundTransfer) {
        AccountsDTO receiverAccount = restTemplate.getForObject(url + "/" + fundTransfer.getReceiver_account_id(),
                AccountsDTO.class);

        AccountsDTO senderAccount = restTemplate.getForObject(url + "/" + fundTransfer.getSender_account_id(),
                AccountsDTO.class);

        if(receiverAccount.getAccountNumber() == null || senderAccount.getAccountNumber() == null) {
            throw new RuntimeException("Invalid Account");
        }

        receiverAccount.setBalance(receiverAccount.getBalance() + fundTransfer.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - fundTransfer.getAmount());

        restTemplate.put(url, senderAccount);
        restTemplate.put(url, receiverAccount);

        return transactionRepository.save(fundTransfer);
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transactions> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }
}
