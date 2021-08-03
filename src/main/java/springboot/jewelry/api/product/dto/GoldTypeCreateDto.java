package springboot.jewelry.api.product.dto;

import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.product.validation.annotation.FormatPercentageGold;
import springboot.jewelry.api.product.validation.annotation.UniqueGoldType;

@Getter
@Setter
public class GoldTypeCreateDto {

    @FormatPercentageGold
    @UniqueGoldType
    private Double percentage;
}
