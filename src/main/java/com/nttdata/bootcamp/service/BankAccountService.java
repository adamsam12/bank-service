package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.model.BankAccount;
import com.nttdata.bootcamp.repository.BankAccountRepository;
import com.nttdata.bootcamp.service.impl.BankAccountServiceImpl;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BankAccountService implements BankAccountServiceImpl {

    private  final BankAccountRepository accountRepository;

    @Override
    public Flux<BankAccount> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> getByIdAccount(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Mono<BankAccount> createAccount(BankAccount account) {
        account.setStatusAccount("Activo");
        return accountRepository.save(account);
    }

    @Override
    public Mono<BankAccount> updateAccount(String id, BankAccount account) {
        return accountRepository.findById(id).flatMap(account1 -> {
            account1.setAvailableBalanceAccount(account.getAvailableBalanceAccount());
            account1.setStatusAccount(account.getStatusAccount());
            return accountRepository.save(account1);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteAccount(String id) {
        return accountRepository.findById(id).flatMap(account -> accountRepository.deleteById(account.getId()));
    }
}
