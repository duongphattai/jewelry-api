package springboot.jewelry.api.commondata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import springboot.jewelry.api.commondata.model.FilterCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class FilterSpecificationImpl<T> implements Specification<T> {

    private List<FilterCriteria> criteriaList;

    public FilterSpecificationImpl() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(FilterCriteria criteria) {
        this.criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        for(FilterCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(cb.greaterThan(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(cb.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case LESS_THAN:
                    predicates.add(cb.lessThan(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(cb.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case EQUAL:
                    predicates.add(cb.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case NOT_EQUAL:
                    predicates.add(cb.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case MATCH:
                    predicates.add(cb.like(
                            cb.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toLowerCase() + "%"));
                    break;
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
