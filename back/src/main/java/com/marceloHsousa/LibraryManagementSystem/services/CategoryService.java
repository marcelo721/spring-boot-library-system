package com.marceloHsousa.LibraryManagementSystem.services;


import com.marceloHsousa.LibraryManagementSystem.entities.category.Category;
import com.marceloHsousa.LibraryManagementSystem.entities.category.exceptions.CategoryNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.repositories.CategoryRepository;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.DatabaseException;
import com.marceloHsousa.LibraryManagementSystem.services.exceptions.ResourcesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findUserById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }else{
            throw new CategoryNotFoundException("Category not Found");
        }
    }

    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public List<Category> saveAll(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    @Transactional
    public void delete(Long id){
        try {
            categoryRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw  new ResourcesNotFoundException(id);

        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public Category update(Long id, Category obj){

        try {
            Category entity = categoryRepository.getReferenceById(id);
            updateData(entity, obj);
            return  categoryRepository.save(entity);

        } catch (EntityNotFoundException e){
            throw new ResourcesNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
    }
}
