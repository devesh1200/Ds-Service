package com.datascience.service;

import com.datascience.dto.ExpenseDto;
import com.datascience.entities.Expense;
import com.datascience.repository.ExpenseRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {


    private final ExpenseRepo expenseRepo;


    private  final ObjectMapper objectMapper;

    public ExpenseService(ExpenseRepo expenseRepo, ObjectMapper objectMapper) {
        this.expenseRepo = expenseRepo;
        this.objectMapper = objectMapper;
    }

    public boolean createExpense(ExpenseDto expenseDto) {
        setCurrency(expenseDto);

        try {

            expenseRepo.save(objectMapper.convertValue(expenseDto, Expense.class));
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    private void setCurrency(ExpenseDto expenseDto) {
        if (Objects.isNull(expenseDto.getCurrency())) {
            expenseDto.setCurrency("INR");
        }
    }



    public boolean updateExpense(ExpenseDto expenseDto) {
        setCurrency(expenseDto);
        Optional<Expense> expenseFoundOpt = expenseRepo.findByUserIdAndExternalId(expenseDto.getUserId(), expenseDto.getExternalId());
        if (expenseFoundOpt.isEmpty()) {
            return false;
        }
        Expense expense = expenseFoundOpt.get();
        expense.setAmount(expenseDto.getAmount());
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency()) ? expenseDto.getCurrency() : expense.getCurrency());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant()) ? expenseDto.getMerchant() : expense.getMerchant());
        expenseRepo.save(expense);
        return true;

    }

    public List<ExpenseDto> getExpense(String userId) {
        List<Expense> listOfExpense = expenseRepo.findByUserId(userId);
        return objectMapper.convertValue(listOfExpense, new TypeReference<List<ExpenseDto>>() {
        });
    }


}
