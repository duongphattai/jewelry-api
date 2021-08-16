package springboot.jewelry.api.product.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.commondata.GenericServiceImpl;
import springboot.jewelry.api.product.dto.GoldTypeCreateDto;
import springboot.jewelry.api.product.model.GoldType;
import springboot.jewelry.api.product.repository.GoldTypeRepository;
import springboot.jewelry.api.product.service.itf.GoldTypeService;
import springboot.jewelry.api.util.MapDtoToModel;

@AllArgsConstructor
@Service
public class GoldTypeServiceImpl extends GenericServiceImpl<GoldType, Long> implements GoldTypeService {

    private GoldTypeRepository goldTypeRepository;
    private MapDtoToModel<Object, GoldType> mapper;

    @Override
    public GoldType save(GoldTypeCreateDto dto) {
        GoldType newGoldType = new GoldType();
        newGoldType = mapper.map(dto, newGoldType);
        return goldTypeRepository.save(newGoldType);
    }

}
