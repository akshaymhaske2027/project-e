package com.akshay.service.income;


import com.akshay.dto.IncomeDto;
import com.akshay.entity.Income;
import com.akshay.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private  final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDto incomeDto){
        return saveOrUpdateIncome(new Income(),incomeDto);
    }



    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDescription(incomeDto.getDescription());
        income.setCategory(incomeDto.getCategory());
        income.setAmount(incomeDto.getAmount());
        income.setDate(incomeDto.getDate());

        return incomeRepository.save(income);
    }

    public List<IncomeDto> getAllIncome(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate))
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public Income updateIncome(Long id,IncomeDto incomeDto){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(),incomeDto);
        }else{
            throw new EntityNotFoundException("Income is not present with id "+id);
        }

    }

    public IncomeDto getIncomeById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDto();
        }else {
            throw new EntityNotFoundException("Income is not present with id"+id );
        }

    }

    public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if (optionalIncome.isPresent()) {
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense is not present with id "+id);
        }
    }


}
