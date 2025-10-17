package com.enterprise.ipmanager.repository;

import com.enterprise.ipmanager.model.IpAddress;
import com.enterprise.ipmanager.model.enums.IpStatus;
import org.springframework.data.jpa.domain.Specification;

public class IpAddressSpecification {

    public static Specification<IpAddress> hasStatus(IpStatus status) {
        return (root, query, criteriaBuilder) ->
            status == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<IpAddress> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("owner")), likePattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("subnet").get("name")), likePattern)
            );
        };
    }
}