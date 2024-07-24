package com.akshay.controller;


import com.akshay.dto.IncomeDto;
import com.akshay.entity.Income;
import com.akshay.service.income.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
public class IncomeController {

    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto incomeDto){
        Income createdIncome = incomeService.postIncome(incomeDto);
        if (createdIncome == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncome(){
        return ResponseEntity.ok(incomeService.getAllIncome());

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id,
                                          @RequestBody IncomeDto incomeDto ){

        try {
            return ResponseEntity.ok(incomeService.updateIncome(id,incomeDto));
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING_WENT_WRONG");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable  Long id){
        try {
            return ResponseEntity.ok(incomeService.getIncomeById(id));
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable  Long id){
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok(null);

        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }






}
