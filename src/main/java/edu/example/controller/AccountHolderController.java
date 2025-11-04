package edu.example.controller;

import edu.example.model.AccountHolder;
import edu.example.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/account")
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @PostMapping("/create")
    public JsonNode createAccount(@RequestBody JsonNode jsonNode){
        return accountHolderService.createAccount(jsonNode);
    }

}
