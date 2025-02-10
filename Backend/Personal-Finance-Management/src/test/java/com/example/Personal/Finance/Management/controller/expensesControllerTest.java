package com.example.Personal.Finance.Management.controller;

import com.example.Personal.Finance.Management.DTO.ExpensesDto;
import com.example.Personal.Finance.Management.Service.budgetService;
import com.example.Personal.Finance.Management.Service.expensesService;
import com.example.Personal.Finance.Management.Enum.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class expensesControllerTest {

    @Mock
    private expensesService expensesService; // Mock the Service Layer

    @Mock
    private budgetService budgetService; // Mock BudgetService

    @InjectMocks
    private expensesController expensesController; // Inject Mocks into Controller

    private ExpensesDto expenseDto;
    private ExpensesDto savedExpenseDto;

    @BeforeEach
    void setUp() {
        expenseDto = new ExpensesDto(150.50, 1L, "Lunch", Category.Wants, 1L, LocalDate.of(2024, 2, 9));
        savedExpenseDto = new ExpensesDto(150.50, 1L, "Lunch", Category.Wants, 1L, LocalDate.of(2024, 2, 9));
    }


    @Test
    void testAddExpenses() {
        when(expensesService.addExpenses(expenseDto)).thenReturn(savedExpenseDto);

        ResponseEntity<?> response = expensesController.addExpenses(expenseDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expensesService, times(1)).addExpenses(expenseDto);
    }


    @Test
    void testGetExpenseById_Found() {
        when(expensesService.getExpenseById(1L)).thenReturn(savedExpenseDto);

        ResponseEntity<?> response = expensesController.getExpenseById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expensesService, times(1)).getExpenseById(1L);
    }


    @Test
    void testGetExpenseById_NotFound() {
        when(expensesService.getExpenseById(1L)).thenThrow(new NoSuchElementException());

        ResponseEntity<?> response = expensesController.getExpenseById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void testGetExpensesByUserId() {
        when(expensesService.getExpensesByUserId(1L)).thenReturn(Arrays.asList(savedExpenseDto));

        ResponseEntity<?> response = expensesController.getExpensesByUserId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, ((List<?>) response.getBody()).size());
        verify(expensesService, times(1)).getExpensesByUserId(1L);
    }


    @Test
    void testUpdateExpense() {
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(savedExpenseDto);
        when(expensesService.updateExpense(eq(1L), any(ExpensesDto.class)))
                .thenReturn(ResponseEntity.ok(savedExpenseDto));

        ResponseEntity<?> response = expensesController.updateExpense(1L, expenseDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expensesService, times(1)).updateExpense(eq(1L), any(ExpensesDto.class));
    }


    @Test
    void testDeleteExpenseById_Success() {
        doNothing().when(expensesService).deleteExpenseById(1L);

        ResponseEntity<?> response = expensesController.deleteExpenseById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(expensesService, times(1)).deleteExpenseById(1L);
    }


    @Test
    void testDeleteExpenseById_NotFound() {
        doThrow(new NoSuchElementException()).when(expensesService).deleteExpenseById(1L);

        ResponseEntity<?> response = expensesController.deleteExpenseById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
