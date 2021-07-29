package springboot.jewelry.api.supplier.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.supplier.dto.SupplierCreateDto;
import springboot.jewelry.api.supplier.dto.SupplierUpdateDto;
import springboot.jewelry.api.supplier.model.Supplier;
import springboot.jewelry.api.supplier.service.SupplierService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/supplier")
public class AdminSupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {
        List<Supplier> suppliers = supplierService.findAll();
        if (suppliers.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(suppliers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addSupplier(@Valid @RequestBody SupplierCreateDto dto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        Supplier supplier = supplierService.save(dto);

        return ResponseHandler.getResponse(supplier, HttpStatus.OK);
    }

    @PutMapping("/{supplier-id}")
    public ResponseEntity<Object> updateSupplier(@PathVariable("supplier-id") Long id,
                                                 @Valid @RequestBody SupplierUpdateDto dto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);
        }

        Supplier supplier = supplierService.updateSupplierInfo(dto, id);

        return ResponseHandler.getResponse(supplier, HttpStatus.OK);
    }

    @DeleteMapping("/{supplier-id}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable("supplier-id") Long id) {

        Optional<Supplier> supplier = supplierService.findById(id);
        if (!supplier.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }

        supplierService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
