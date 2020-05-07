package com.modus.create.financialtransactions;

import com.modus.create.utils.UtilsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
@Import(UtilsConfiguration.class)
public class FinancialTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialTransactionsApplication.class, args);
    }

}
