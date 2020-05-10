package com.boob.medical.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

/**
 * 查询操作工具类
 */
public class SpecificationUtils {

    public static <T> Specification<T> getLikeSpecification(String properties, String coreKeyWord) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate.getExpressions().add(criteriaBuilder.like(root.get(properties),
                    "%" + coreKeyWord + "%"));
            return predicate;
        };
    }
}
