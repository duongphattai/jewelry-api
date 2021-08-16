package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.product.dto.CategoryCreateDto;
import springboot.jewelry.api.product.dto.CategoryUpdateDto;
import springboot.jewelry.api.product.model.Category;
import springboot.jewelry.api.product.repository.CategoryRepository;
import springboot.jewelry.api.product.service.itf.CategoryService;
import springboot.jewelry.api.util.MapDtoToModel;

@AllArgsConstructor
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

    private CategoryRepository categoryRepository;
    private MapDtoToModel<Object, Category> mapper;

    @Override
    public Category save(CategoryCreateDto dto) {
        Category newCategory = new Category();
        newCategory = mapper.map(dto, newCategory);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateProductTypeInfo(CategoryUpdateDto dto, Long id) {
        Category categoryUpdated = categoryRepository.getOne(id);
        categoryUpdated = mapper.map(dto, categoryUpdated);
        return categoryRepository.save(categoryUpdated);
    }

}
