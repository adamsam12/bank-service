package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {

}
