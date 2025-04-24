package com.datascience.controller;


import com.datascience.dto.ExpenseDto;
import com.datascience.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")

public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;



    @GetMapping("/getExpense")
    public ResponseEntity<List<ExpenseDto>> getExpense(@PathParam("user_id") @NotNull String userId) {
        try {
            List<ExpenseDto> expenseDto = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
