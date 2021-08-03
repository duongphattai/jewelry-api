package springboot.jewelry.api.supplier.service;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.supplier.dto.SupplierCreateDto;
import springboot.jewelry.api.supplier.dto.SupplierUpdateDto;
import springboot.jewelry.api.supplier.model.Supplier;

public interface SupplierService extends GenericService<Supplier, Long> {
    Supplier save(SupplierCreateDto dto);

    Supplier updateSupplierInfo(SupplierUpdateDto dto, Long id);
}
