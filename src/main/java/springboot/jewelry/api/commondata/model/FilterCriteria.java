package springboot.jewelry.api.commondata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FilterCriteria {
    private String key;
    private String value;
    private FilterOperation operation;
}
