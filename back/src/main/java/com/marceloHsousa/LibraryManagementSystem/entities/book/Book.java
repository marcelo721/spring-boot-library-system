package com.marceloHsousa.LibraryManagementSystem.entities.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marceloHsousa.LibraryManagementSystem.entities.category.Category;
import com.marceloHsousa.LibraryManagementSystem.entities.author.Author;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;

    @Column(name="available")
    private Boolean available;

    @ManyToOne(fetch = FetchType.EAGER,  cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Getter(AccessLevel.NONE)
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }
}
