package org.example.projectbcms.repositories;

import org.example.projectbcms.models.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
}
