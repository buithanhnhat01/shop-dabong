package edu.poly.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByName(String name);
}

