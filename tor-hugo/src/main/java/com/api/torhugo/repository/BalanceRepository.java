package com.api.torhugo.repository;

import com.api.torhugo.model.entity.BalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceModel, Long> {
    @Query(value = " SELECT * FROM TB_BALANCE WHERE ID_WALLET = :idWallet ", nativeQuery = true)
    List<BalanceModel> findAllBalanceByWalletId(@Param("idWallet") Long idWallet );
    @Query(value = " SELECT * FROM TB_BALANCE WHERE TYPE_BALANCE = 0", nativeQuery = true)
    List<BalanceModel> findAllBalanceByDeposit();
    @Query(value = " SELECT * FROM TB_BALANCE WHERE TYPE_BALANCE = 1", nativeQuery = true)
    List<BalanceModel> findAllBalanceByOutflow();
}
