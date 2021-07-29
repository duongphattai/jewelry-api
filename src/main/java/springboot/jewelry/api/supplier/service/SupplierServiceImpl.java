package springboot.jewelry.api.supplier.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.supplier.dto.SupplierCreateDto;
import springboot.jewelry.api.supplier.dto.SupplierUpdateDto;
import springboot.jewelry.api.supplier.model.Supplier;
import springboot.jewelry.api.supplier.repository.SupplierRepository;
import springboot.jewelry.api.util.MapDtoToModel;

@AllArgsConstructor
@Service
public class SupplierServiceImpl extends GenericServiceImpl<Supplier, Long> implements SupplierService {

    private SupplierRepository supplierRepository;
    private MapDtoToModel<Object, Supplier> mapper;


    @Override
    public Supplier save(SupplierCreateDto dto) {
        Supplier supplier = new Supplier();
        supplier = mapper.map(dto, supplier);

        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplierInfo(SupplierUpdateDto dto, Long id) {
        Supplier supplier = supplierRepository.getOne(id);
        supplier = mapper.map(dto, supplier);

        return supplierRepository.save(supplier);
    }
}
