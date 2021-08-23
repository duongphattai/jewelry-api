package springboot.jewelry.api.commondata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagedResult<T> {

    private List<T> elements;
    private long totalElements;
    private int totalPages;
    private int currentPage;

}
