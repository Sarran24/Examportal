package com.examportal.service.exam;

import com.examportal.model.exam.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category) throws Exception;

    Category update(Category category);

    void delete(Long cid);

    List<Category> getAll();

    Category getById(Long cid);


}
