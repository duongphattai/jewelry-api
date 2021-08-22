package springboot.jewelry.api.commondata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String value;
    private SearchOperation operation;
}
