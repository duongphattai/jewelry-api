package springboot.jewelry.api.product.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.product.dto.GoldTypeCreateDto;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.projection.GoldTypeProjection;
import springboot.jewelry.api.product.service.itf.GoldTypeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product/gold-type")
public class AdminGoldTypeController {

    @Autowired
    private GoldTypeService goldTypeService;

    @GetMapping("")
    public ResponseEntity<Object> findAll() {

        List<GoldTypeProjection> goldTypeProjections = goldTypeService.findAllBy(GoldTypeProjection.class);
        if (goldTypeProjections.isEmpty()) {
            return ResponseHandler.getResponse("Danh sách trống!", HttpStatus.OK);
        }

        return ResponseHandler.getResponse(goldTypeProjections, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addGoldType(@Valid @RequestBody GoldTypeCreateDto dto,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        GoldType newGoldType = goldTypeService.save(dto);

        return ResponseHandler.getResponse(newGoldType, HttpStatus.OK);
    }

    @DeleteMapping("/{gold-type-id}")
    public ResponseEntity<Object> deleteGoldType(@PathVariable("gold-type-id") Long id) {

        Optional<GoldType> goldType = goldTypeService.findById(id);
        if (!goldType.isPresent()) {
            return ResponseHandler.getResponse("Không tìm thấy ID: " + id, HttpStatus.OK);
        }

        goldTypeService.deleteById(id);

        return ResponseHandler.getResponse(HttpStatus.OK);
    }
}
