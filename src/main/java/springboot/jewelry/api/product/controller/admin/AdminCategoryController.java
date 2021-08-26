package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.CategoryCreateDto;
import springboot.jewelry.api.product.dto.CategoryUpdateDto;
import springboot.jewelry.api.product.model.Category;
import springboot.jewelry.api.product.projection.CategoryProjection;
import springboot.jewelry.api.product.service.itf.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/products/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {

        List<CategoryProjection> categoryProjections = categoryService.findAllBy(CategoryProjection.class);
        if (categoryProjections.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(categoryProjections, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryCreateDto dto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Category newCategory = categoryService.save(dto);

        return ResponseHandler.getResponse(newCategory, HttpStatus.OK);
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("category-id") Long id,
                                                @Valid @RequestBody CategoryUpdateDto dto,
                                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Category categoryUpdate = categoryService.updateCategoryInfo(dto, id);

        return ResponseHandler.getResponse(categoryUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("category-id") Long id) {

        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy sản phẩm có ID: " + id, HttpStatus.OK);
        }
        categoryService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
