package springboot.jewelry.api.product.service.itf;

import springboot.jewelry.api.commondata.GenericService;
import springboot.jewelry.api.product.dto.GoldTypeCreateDto;
import springboot.jewelry.api.product.model.GoldType;

public interface GoldTypeService extends GenericService<GoldType, Long> {

    GoldType save(GoldTypeCreateDto dto);
}
