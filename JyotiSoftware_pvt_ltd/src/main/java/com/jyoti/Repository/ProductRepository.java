package com.jyoti.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyoti.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
