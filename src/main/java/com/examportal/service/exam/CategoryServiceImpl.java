package com.examportal.service.exam;

import com.examportal.model.exam.Category;
import com.examportal.repository.exam.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) throws Exception {
        Optional<Category> existCategory = categoryRepository.findByTitle(category.getTitle());
        if(existCategory.isPresent()){
            throw new Exception("with this cid data is present");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Optional<Category> existCategory = categoryRepository.findById(category.getCid());
        if(existCategory.isPresent()) {
            return categoryRepository.save(category);
        }else{
            throw new EntityNotFoundException("Data is not present");
        }
    }

    @Override
    public void delete(Long cid) {
        Optional<Category> existCategory = categoryRepository.findById(cid);
        if(existCategory.isPresent()) {
            categoryRepository.deleteById(cid);
        }else{
            throw  new EntityNotFoundException("category with this id is not present");
        }
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long cid) {
        return categoryRepository.findById(cid).orElseThrow(()-> new EntityNotFoundException("Category is not present"));
    }
}
