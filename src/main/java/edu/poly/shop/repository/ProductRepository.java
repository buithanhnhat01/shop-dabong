package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long	> {
	@Query("FROM Product p Where p.productId=?1")	
	Product findId(Long id);
}
