package springboot.jewelry.api.product.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.product.dto.CategoryCreateDto;
import springboot.jewelry.api.product.dto.CategoryUpdateDto;
import springboot.jewelry.api.product.model.Category;

public interface CategoryService extends GenericService<Category, Long> {

    Category save(CategoryCreateDto dto);

    Category updateProductTypeInfo(CategoryUpdateDto dto, Long id);

}
