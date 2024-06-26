package com.marceloHsousa.LibraryManagementSystem.repositories;


import com.marceloHsousa.LibraryManagementSystem.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
