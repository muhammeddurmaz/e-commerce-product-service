package com.durmaz.product.repository;

import com.durmaz.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select price from products where id = :id" , nativeQuery = true)
    Optional<Double> getProductPrice(@Param("id") Long id);

    @Query(value = "select sum(price) from products where id in  :ids" , nativeQuery = true)
    Optional<Double> getSumProductPriceByIds(@Param("ids") List<Long> ids);
}
