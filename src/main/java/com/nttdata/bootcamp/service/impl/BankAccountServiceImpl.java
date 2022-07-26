package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountServiceImpl {

    Flux<BankAccount> getAllAccounts();

    Mono<BankAccount> getByIdAccount(String id);

    Mono<BankAccount> createAccount(BankAccount account);

    Mono<BankAccount> updateAccount(String id, BankAccount account);

    Mono<Void> deleteAccount(String id);

}
