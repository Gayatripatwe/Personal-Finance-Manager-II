package com.example.Personal.Finance.Management.controller;

import com.example.Personal.Finance.Management.DTO.BudgetDto;
import com.example.Personal.Finance.Management.DTO.BudgetResponseDto;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.Enum.Category;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Income;
import com.example.Personal.Finance.Management.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class budgetControllerTest {

    @Mock
    private budgetService budgetService; // Mocking the Service Layer

    @InjectMocks
    private budgetController budgetController; // Injecting Mocks into the Controller

    private BudgetDto budgetDto;
    private Budget budget;
    private BudgetResponseDto budgetResponseDto;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUserid(1L);
        Income income = new Income();
        income.setId(1L);
        budgetDto = new BudgetDto(5000.0, 1L, null, Category.Wants);
        budget = new Budget(1L, 5000.0,user ,  income, Category.Wants);
        budgetResponseDto = new BudgetResponseDto(1L, 5000.0, Category.Wants, 1L);
    }


    @Test
    void testAddBudget() {
        when(budgetService.addBudget(budgetDto)).thenReturn(budget);

        ResponseEntity<?> response = budgetController.addBudget(budgetDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(budgetService, times(1)).addBudget(budgetDto);
    }


    @Test
    void testGetBudgetById() {
        when(budgetService.getBudgetById(1L)).thenReturn(budget);

        ResponseEntity<?> response = budgetController.getBudgetById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(budgetService, times(1)).getBudgetById(1L);
    }


    @Test
    void testGetBudgetsByUserId() {
        when(budgetService.getBudgetsByUserId(1L)).thenReturn(Arrays.asList(budgetResponseDto));

        ResponseEntity<List<BudgetResponseDto>> response = budgetController.getBudgetsByUserId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(budgetService, times(1)).getBudgetsByUserId(1L);
    }


    @Test
    void testDeleteBudget() {
        when(budgetService.deleteBudget(1L)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<Void> response = budgetController.deleteBudget(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(budgetService, times(1)).deleteBudget(1L);
    }


    @Test
    void testAddBudgetByUserId() {
        when(budgetService.addBudgetbyuserid(budgetDto)).thenReturn(budgetResponseDto);

        ResponseEntity<?> response = budgetController.addBudgetbyuserid(budgetDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(budgetService, times(1)).addBudgetbyuserid(budgetDto);
    }


    @Test
    void testUpdateBudget() {
        when(budgetService.updateBudget(eq(1L), any(BudgetDto.class))).thenReturn(budget);

        ResponseEntity<BudgetDto> response = budgetController.updateBudget(1L, budgetDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(budgetService, times(1)).updateBudget(eq(1L), any(BudgetDto.class));
    }
}
