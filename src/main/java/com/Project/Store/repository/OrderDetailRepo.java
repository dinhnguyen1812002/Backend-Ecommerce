package com.Project.Store.repository;

import com.Project.Store.entity.OrderDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepo extends CrudRepository<OrderDetail, Integer> {


}
