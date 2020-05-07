package com.modus.create.financialbalance;

import com.modus.create.utils.UtilsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
@Import(UtilsConfiguration.class)
public class FinancialBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialBalanceApplication.class, args);
    }

}
