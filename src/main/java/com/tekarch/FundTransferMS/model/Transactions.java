package com.tekarch.FundTransferMS.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long transferId;
    @NotNull
    private Long sender_account_id;
    @NotNull
    private  Long receiver_account_id;
    @NotNull
    private double amount;
    private String status;
    @CreationTimestamp
    private LocalDateTime initiated_at;
    @UpdateTimestamp
    private LocalDateTime completed_at;


}
