package com.gwh.manager.repositories;

import com.gwh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product>{
}
