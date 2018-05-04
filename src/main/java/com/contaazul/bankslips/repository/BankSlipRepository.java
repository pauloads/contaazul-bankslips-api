package com.contaazul.bankslips.repository;

import com.contaazul.bankslips.model.BankSlip;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Paulo
 */
@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, UUID>{
    
    @Query("select new com.contaazul.bankslips.model.BankSlip(b.id, b.dueDate, b.totalInCents, b.customer) from BankSlip b")
    List<BankSlip> getAllWithoutDetails();
}
