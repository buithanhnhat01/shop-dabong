package edu.poly.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.OrderDetail;

@Service
public interface OderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    
	/*OrderDetail add(long id);*/
    @Query("FROM OrderDetail o WHERE o.product.productId=?1")
    List<OrderDetail> list(Long id);
    
    @Query("FROM OrderDetail o WHERE o.product.productId=?1")
    OrderDetail findByDetail(Long id);

}
