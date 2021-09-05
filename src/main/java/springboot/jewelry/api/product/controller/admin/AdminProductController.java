package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.commondata.model.PagedResult;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.commondata.model.SearchCriteria;
import springboot.jewelry.api.product.dto.*;
import springboot.jewelry.api.product.model.Product;
import springboot.jewelry.api.product.projection.ProductSummaryProjection;
import springboot.jewelry.api.product.service.itf.ProductService;
import springboot.jewelry.api.util.MessageUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        PagedResult<ProductSummaryProjection> products = productService.findProductsSummary(pageable);
        return ResponseHandler.getResponse(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findAllWithSearch(
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "searchCriteria[keys][]") List<String> keys,
            @RequestParam(value = "searchCriteria[value]") String value) {

        SearchCriteria searchCriteria = new SearchCriteria(keys, value);
        PagedResult<ProductSummaryDto> productsFiltered
                = productService.findProductsSummaryWithSearch(searchCriteria, pageable);
        return ResponseHandler.getResponse(productsFiltered, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> findProductById(@PathVariable(value = "productId") Long productId) {

        ProductDetailsAdminDto product = productService.findProductById(productId);
        if(product == null) {
            return ResponseHandler.getResponse("Không tìm thấy sản phẩm có ID: " + productId, HttpStatus.OK);
        }
        return ResponseHandler.getResponse(product, HttpStatus.OK);
    }


    @PostMapping(value = "",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> addProduct(@RequestPart("dto") @Valid ProductCreateDto dto,
                                             @RequestPart(value = "images[]", required = false) List<MultipartFile> images,
                                             @RequestPart(value = "avatar", required = false) MultipartFile avatar,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }
        if(images != null) dto.setImages(images);
        if(avatar != null) dto.setAvatar(avatar);

        ProductDetailsDto newProduct = productService.save(dto);
        return ResponseHandler.getResponse(newProduct, HttpStatus.OK);
    }

    @PutMapping(value = "/{productId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> updateProduct(@PathVariable("productId") Long id,
                                                @RequestPart("dto") @Valid ProductUpdateDto dto,
                                                @RequestPart(value = "images[]", required = false) List<MultipartFile> images,
                                                @RequestPart(value = "avatar", required = false) MultipartFile avatar,
                                                BindingResult bindingResult) throws GeneralSecurityException, IOException {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        if(images != null) dto.setNewImages(images);
        if(avatar != null) dto.setAvatar(avatar);

        Product productUpdated = productService.updateProductInfo(dto, id);

        return ResponseHandler.getResponse(new MessageUtils("Cập nhật thành công"), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("productId") Long id) {

        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }
        productService.deleteById(id);
        return ResponseHandler.getResponse(HttpStatus.OK);
    }

}
