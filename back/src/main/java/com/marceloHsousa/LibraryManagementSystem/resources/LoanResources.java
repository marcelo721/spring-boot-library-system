package com.marceloHsousa.LibraryManagementSystem.resources;

import com.marceloHsousa.LibraryManagementSystem.entities.loan.Loan;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanResources {

    private LoanService service;

    @Autowired
    public LoanResources(LoanService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Loan> findLoansByUser(@RequestBody User user){
        return service.findLoansByUser(user);
    }
}
