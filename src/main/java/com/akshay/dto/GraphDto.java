package com.akshay.dto;

import com.akshay.entity.Expense;
import com.akshay.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;


}
