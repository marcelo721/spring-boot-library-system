package com.marceloHsousa.LibraryManagementSystem.repositories;

import com.marceloHsousa.LibraryManagementSystem.entities.loan.Loan;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(value = "SELECT * FROM loans WHERE loans.user_id=:#{#user.id}",
           nativeQuery = true)
    List<Loan> findLoansByUser(@Param("user") User user);
}
