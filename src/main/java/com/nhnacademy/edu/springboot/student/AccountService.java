package com.nhnacademy.edu.springboot.student;

import java.util.List;

public interface AccountService {

    List<Account> getAccount();

    Account getAccount(Long id);

    Account createAccount(Account account);

    void deleteAccount(Long id);
}
