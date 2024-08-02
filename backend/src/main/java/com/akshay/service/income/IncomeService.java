package com.akshay.service.income;

import com.akshay.dto.IncomeDto;
import com.akshay.entity.Income;

import java.util.List;

public interface IncomeService {


    Income postIncome(IncomeDto incomeDto);

    List<IncomeDto> getAllIncome();

    Income updateIncome(Long id,IncomeDto incomeDto);

    IncomeDto getIncomeById(Long id);


    void deleteIncome(Long id);
}
