package org.example.projectbcms.repositories;

import org.example.projectbcms.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
