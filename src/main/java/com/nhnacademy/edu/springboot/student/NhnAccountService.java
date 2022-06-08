package com.nhnacademy.edu.springboot.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhnAccountService implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Account createAccount(Account account) {
        boolean bl = accountRepository.findById(account.getId()).isPresent();
        if(bl){
            throw new IllegalArgumentException("already exists" + account.getId());
        }
        return accountRepository.save(account);
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
