package edu.example.service;

import edu.example.model.AccountHolder;
import edu.example.repository.AccountHolderRepository;
import edu.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService {
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    final ObjectMapper objectMapper = new ObjectMapper();


    public JsonNode createAccount(JsonNode jsonNode) {
        ObjectNode response = objectMapper.createObjectNode();

        AccountHolder accountHolder;
        if(jsonNode.has("accountId")) {
            Long accountId = jsonNode.get("accountId").asLong();
            Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(accountId);

            if (optionalAccountHolder.isPresent()) {
                accountHolder = optionalAccountHolder.get();
            } else {
                response.put("status", "error");
                response.put("message", "Account does not exist");
                return response;
            }
        }else {
            accountHolder = new AccountHolder();

        }
        accountHolder.setBalance(jsonNode.has("Balance")?jsonNode.get("balance").asDouble():0.00);
        accountHolder.setAccountHolderName(jsonNode.has("accountHolderName")?jsonNode.get("accountHolderName").asText():null);

        accountHolderRepository.save(accountHolder);

        response.put("status","success");
        response.put("message","Account created or update successfully");
        response.put("accountId",accountHolder.getAccountId());

        return response;

    }

    public JsonNode getAllAccountHolder() {

        ObjectNode response = objectMapper.createObjectNode();
        List<AccountHolder> accountHolderList = accountHolderRepository.findAll();
        if (accountHolderList.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No account holder found");
            return response;
        }
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (AccountHolder accountHolder:accountHolderList) {
            ObjectNode accountNode = objectMapper.createObjectNode();

            accountNode.put("accountId", accountHolder.getAccountId());
            accountNode.put("balance",accountHolder.getBalance());
            accountNode.put("accountHolderName",accountHolder.getAccountHolderName());

            arrayNode.add(accountNode);
        }
        response.put("status","success");
        response.put("message","fetched the data successfully");
        response.set("AccountHolder",arrayNode);

        return response;
    }
}
