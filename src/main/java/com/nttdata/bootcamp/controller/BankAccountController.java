package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.BankAccount;
import com.nttdata.bootcamp.service.BankAccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class BankAccountController {

    private final BankAccountService accountService;

    private static final String ACCOUNT = "account";

    @GetMapping(value = "/allAccounts")
    @ResponseStatus(HttpStatus.OK)
    public Flux<BankAccount> getAllAccounts(){
        System.out.println("Listar todas las cuentas bancarias.");
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BankAccount> getAccountById(@PathVariable String id){
        System.out.println("Buscar cuenta bancaria por Id.");
        return accountService.getByIdAccount(id);
    }

    @PostMapping(value = "/create")
    @CircuitBreaker(name = ACCOUNT, fallbackMethod = "account")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankAccount> createAccount(@RequestBody BankAccount account){
        System.out.println("cuenta bancaria creada.");
        return accountService.createAccount(account);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = ACCOUNT, fallbackMethod = "account")
    public Mono<BankAccount> updateAccount(@PathVariable String id, @RequestBody BankAccount account){
        System.out.println("Actualizado.");
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteAccount(@PathVariable String id){
        System.out.println("se elimino la cuenta.");
        return accountService.deleteAccount(id);
    }
}
