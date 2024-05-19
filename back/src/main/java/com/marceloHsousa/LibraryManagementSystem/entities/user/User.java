package com.marceloHsousa.LibraryManagementSystem.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marceloHsousa.LibraryManagementSystem.entities.loan.Loan;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="role")
    private String role;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @Getter(AccessLevel.NONE)
    private List<Loan> loans;

    public void addLoan(Loan loan){
        if (this.loans==null){
            this.loans=new ArrayList<>();
        }
        this.loans.add(loan);
    }

    @JsonIgnore
    public List<Loan> getLoan() {
        return loans;
    }
}
