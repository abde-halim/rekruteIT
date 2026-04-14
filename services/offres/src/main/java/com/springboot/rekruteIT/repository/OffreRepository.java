package com.springboot.rekruteIT.repository;

import com.springboot.rekruteIT.entity.Offre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Integer> {

    @Query("SELECT o FROM Offre o WHERE o.recruteurId = :recruteurId")
    List<Offre> findByRecruteurId(@Param("recruteurId") Long recruteurId);

    Page<Offre> findAll(Specification<Offre> spec, Pageable pageable);
}
