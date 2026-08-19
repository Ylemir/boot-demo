package me.boot.datajpa.criteria.core;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import me.boot.datajpa.criteria.util.QueryPredicateUtils;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.domain.Specification;

/**
 * QuerySpecification
 *
 * @since 2023/12/26
 **/
@AllArgsConstructor(staticName = "of")
public class QuerySpecification<T> implements Specification<T> {

    private final Object queryBean;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query,
        @NonNull CriteriaBuilder criteriaBuilder) {
        return QueryPredicateUtils.toPredicate(root, criteriaBuilder, queryBean);
    }

}
