package com.marceloHsousa.LibraryManagementSystem.services;

import com.marceloHsousa.LibraryManagementSystem.entities.loan.Loan;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private LoanRepository repository;

    @Autowired
    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public List<Loan> findLoansByUser(User user){
        return repository.findLoansByUser(user);
    }
}
