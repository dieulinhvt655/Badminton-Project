package org.example.projectbcms.repositories;

import org.example.projectbcms.models.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
