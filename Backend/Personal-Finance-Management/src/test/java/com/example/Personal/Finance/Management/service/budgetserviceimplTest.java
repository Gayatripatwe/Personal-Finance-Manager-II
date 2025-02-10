package com.example.Personal.Finance.Management.service;

import com.example.Personal.Finance.Management.DTO.BudgetDto;
import com.example.Personal.Finance.Management.DTO.BudgetResponseDto;
import com.example.Personal.Finance.Management.Repository.IncomeRepository;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Repository.budgetRepository;
import com.example.Personal.Finance.Management.Service.impl.budgetServiceImpl;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import com.example.Personal.Finance.Management.Enum.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class budgetserviceimplTest {

    @Mock
    private budgetRepository budgetRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @InjectMocks
    private budgetServiceImpl budgetService;

    private BudgetDto budgetDto;
    private Budget budget;
    private BudgetResponseDto budgetResponseDto;
    private User user;
    private Income income;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserid(1L);

        income = new Income();
        income.setId(1L);
        income.setAmount(10000.0);

        budgetDto = new BudgetDto(5000.0, 1L, 1L, Category.Wants);
        budget = new Budget(1L, 5000.0, user, income, Category.Wants);
        budgetResponseDto = new BudgetResponseDto(1L, 5000.0, Category.Wants, 1L);
    }


    @Test
    void testAddBudget() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);

        Budget result = budgetService.addBudget(budgetDto);

        assertNotNull(result);
        assertEquals(5000.0, result.getAmount());
        verify(budgetRepository, times(1)).save(any(Budget.class));
    }


    @Test
    void testAddBudget_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> budgetService.addBudget(budgetDto));
        assertEquals("User not found", exception.getMessage());
    }


    @Test
    void testAddBudget_IncomeNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(incomeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> budgetService.addBudget(budgetDto));
        assertEquals("Income not found", exception.getMessage());
    }


    @Test
    void testGetBudgetById() {
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));

        Budget result = budgetService.getBudgetById(1L);

        assertNotNull(result);
        assertEquals(5000.0, result.getAmount());
    }


    @Test
    void testGetBudgetById_NotFound() {
        when(budgetRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> budgetService.getBudgetById(1L));
        assertEquals("Budget not found", exception.getMessage());
    }


    @Test
    void testGetBudgetsByUserId() {
        when(budgetRepository.findByUser_Id(1L)).thenReturn(Arrays.asList(budget));

        List<BudgetResponseDto> result = budgetService.getBudgetsByUserId(1L);

        assertEquals(1, result.size());
        assertEquals(5000.0, result.get(0).getAmount());
    }


    @Test
    void testDeleteBudget_Success() {
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));
        doNothing().when(budgetRepository).delete(budget);

        ResponseEntity<Void> response = budgetService.deleteBudget(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(budgetRepository, times(1)).delete(budget);
    }


    @Test
    void testDeleteBudget_NotFound() {
        when(budgetRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = budgetService.deleteBudget(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(budgetRepository, never()).delete(any(Budget.class));
    }


    @Test
    void testAddBudgetByUserId() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(incomeRepository.getTotalIncomeByUserId(1L)).thenReturn(10000.0);
        when(budgetRepository.save(any(Budget.class))).thenReturn(budget);

        BudgetResponseDto result = budgetService.addBudgetbyuserid(budgetDto);

        assertNotNull(result);
        assertEquals(5000.0, result.getAmount());
        verify(budgetRepository, times(1)).save(any(Budget.class));
    }


    @Test
    void testAddBudgetByUserId_NotEnoughFunds() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(incomeRepository.getTotalIncomeByUserId(1L)).thenReturn(3000.0);

        Exception exception = assertThrows(RuntimeException.class, () -> budgetService.addBudgetbyuserid(budgetDto));
        assertEquals("Budget amount cannot be greater than total available income.", exception.getMessage());
    }
}
