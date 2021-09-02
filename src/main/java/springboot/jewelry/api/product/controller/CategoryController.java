package springboot.jewelry.api.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.projection.CategoryWithSlugProjection;
import springboot.jewelry.api.product.service.itf.CategoryService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {

        List<CategoryWithSlugProjection> categoryWithSlugProjection
                = categoryService.findAllBy(CategoryWithSlugProjection.class);
        if (categoryWithSlugProjection.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(categoryWithSlugProjection, HttpStatus.OK);
    }
}
