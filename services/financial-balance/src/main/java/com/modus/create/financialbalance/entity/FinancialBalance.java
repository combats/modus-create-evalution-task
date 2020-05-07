package com.modus.create.financialbalance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialBalance {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private int monetaryBalance;
}
