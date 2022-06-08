package com.nhnacademy.edu.springboot.student;

import com.nhnacademy.edu.springboot.student.config.AccountAuthorProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class AccountWebController {

    private final AccountService accountService;

    private final AccountAuthorProperties accountAuthorProperties;

    @Value("${nhn.student.name}")
    private String propertiesName;

    @GetMapping("/web/accounts/{id}")
    public String getStudent(@PathVariable Long id, ModelMap modelmap){
        modelmap.put("account", accountService.getAccount(id));
        modelmap.put("propertiesName", propertiesName);
        modelmap.put("author", accountAuthorProperties);
        return "account";
    }
}
