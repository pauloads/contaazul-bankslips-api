package com.contaazul.bankslips.repository;

import com.contaazul.bankslips.model.BankSlip;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Paulo
 */
@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, UUID>{
    
}
