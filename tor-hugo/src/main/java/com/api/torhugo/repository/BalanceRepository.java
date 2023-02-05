package com.api.torhugo.repository;

import com.api.torhugo.domain.entity.BalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceModel, Long> {
    @Query(value = " SELECT * FROM TB_BALANCE WHERE ID_WALLET = :idWallet ", nativeQuery = true)
    List<BalanceModel> findAllBalanceByWalletId(@Param("idWallet") Long idWallet );
}
