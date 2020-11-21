package com.manoloscorp.coupommachine.company.repository;

import com.manoloscorp.coupommachine.company.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {
}