package com.api.torhugo.repository;

import com.api.torhugo.domain.entity.BalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceModel, Long> {
}
