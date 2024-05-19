package com.marceloHsousa.LibraryManagementSystem.entities.author;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="authors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="nationality")
    private String nationality;

}
