package com.example.Personal.Finance.Management.service;

import com.example.Personal.Finance.Management.DTO.ExpensesDto;
import com.example.Personal.Finance.Management.Repository.UserRepository;
import com.example.Personal.Finance.Management.Repository.budgetRepository;
import com.example.Personal.Finance.Management.Repository.expensesRepository;
import com.example.Personal.Finance.Management.Service.impl.expensesserviceImpl;
import com.example.Personal.Finance.Management.entity.Budget;
import com.example.Personal.Finance.Management.entity.Expenses;
import com.example.Personal.Finance.Management.entity.User;
import com.example.Personal.Finance.Management.Enum.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class expensesserviceimplTest {

    @Mock
    private expensesRepository expensesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private budgetRepository budgetRepository;

    @InjectMocks
    private expensesserviceImpl expensesService;

    private ExpensesDto expenseDto;
    private Expenses expense;
    private User user;
    private Budget budget;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserid(1L);

        budget = new Budget();
        budget.setId(1L);
        budget.setAmount(5000.0);

        expenseDto = new ExpensesDto(150.50, 1L, "Lunch", Category.Wants, 1L, LocalDate.of(2024, 2, 9));
        expense = new Expenses(1L, "Lunch", 150.50, user, LocalDate.of(2024, 2, 9), Category.Wants, budget);
    }

    // ✅ Test: Add Expense
    @Test
    void testAddExpenses() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));
        when(expensesRepository.save(any(Expenses.class))).thenReturn(expense);

        ExpensesDto result = expensesService.addExpenses(expenseDto);

        assertNotNull(result);
        assertEquals(150.50, result.getAmount());
        verify(expensesRepository, times(1)).save(any(Expenses.class));
    }

    // ✅ Test: Add Expense - User Not Found
    @Test
    void testAddExpenses_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> expensesService.addExpenses(expenseDto));
        assertEquals("User not found.", exception.getMessage());
    }

    // ✅ Test: Add Expense - Budget Not Found
    @Test
    void testAddExpenses_BudgetNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(budgetRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> expensesService.addExpenses(expenseDto));
        assertEquals("Budget not found.", exception.getMessage());
    }

    // ✅ Test: Add Expense - Exceeds Budget
    @Test
    void testAddExpenses_ExceedsBudget() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(budget));

        expenseDto.setAmount(6000.0); // More than the budget amount

        Exception exception = assertThrows(IllegalArgumentException.class, () -> expensesService.addExpenses(expenseDto));
        assertEquals("Expense cannot be more than the available budget.", exception.getMessage());
    }

    // ✅ Test: Get Expense by ID
    @Test
    void testGetExpenseById() {
        when(expensesRepository.findById(1L)).thenReturn(Optional.of(expense));

        ExpensesDto result = expensesService.getExpenseById(1L);

        assertNotNull(result);
        assertEquals("Lunch", result.getDescription());
    }

    // ✅ Test: Get Expense by ID - Not Found
    @Test
    void testGetExpenseById_NotFound() {
        when(expensesRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> expensesService.getExpenseById(1L));
        assertEquals("Expense not found.", exception.getMessage());
    }

    // ✅ Test: Get Expenses by User ID
    @Test
    void testGetExpensesByUserId() {
        when(expensesRepository.findByUserId(1L)).thenReturn(Arrays.asList(expense));

        List<ExpensesDto> result = expensesService.getExpensesByUserId(1L);

        assertEquals(1, result.size());
        assertEquals(150.50, result.get(0).getAmount());
    }

    // ✅ Test: Delete Expense by ID (Success)
    @Test
    void testDeleteExpenseById_Success() {
        when(expensesRepository.existsById(1L)).thenReturn(true);
        doNothing().when(expensesRepository).deleteById(1L);

        assertDoesNotThrow(() -> expensesService.deleteExpenseById(1L));
        verify(expensesRepository, times(1)).deleteById(1L);
    }

    // ✅ Test: Delete Expense by ID (Not Found)
    @Test
    void testDeleteExpenseById_NotFound() {
        when(expensesRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(NoSuchElementException.class, () -> expensesService.deleteExpenseById(1L));
        assertEquals("Expense not found.", exception.getMessage());
    }

    // ✅ Test: Update Expense
    @Test
    void testUpdateExpense() {
        when(expensesRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(expensesRepository.save(any(Expenses.class))).thenReturn(expense);

        ResponseEntity<ExpensesDto> response = expensesService.updateExpense(1L, expenseDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Lunch", response.getBody().getDescription());
    }

    // ✅ Test: Update Expense (Not Found)
    @Test
    void testUpdateExpense_NotFound() {
        when(expensesRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ExpensesDto> response = expensesService.updateExpense(1L, expenseDto);

        assertEquals(404, response.getStatusCodeValue());
    }
}
