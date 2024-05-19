package com.marceloHsousa.LibraryManagementSystem.entities.loan;

import com.marceloHsousa.LibraryManagementSystem.entities.book.Book;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="loans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                  CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    private Book book;
}
