package edu.example.controller;

import edu.example.model.AccountHolder;
import edu.example.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/account")
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @PostMapping("/create")
    public JsonNode createAccount(@RequestBody JsonNode jsonNode) {
        return accountHolderService.createAccount(jsonNode);
    }
    @GetMapping("/getAll")
    public JsonNode getAllAccountHolder(){
        return accountHolderService.getAllAccountHolder();
    }

}
