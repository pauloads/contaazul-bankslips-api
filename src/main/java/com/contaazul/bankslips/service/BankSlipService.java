package com.contaazul.bankslips.service;

import com.contaazul.bankslips.model.BankSlip;
import com.contaazul.bankslips.repository.BankSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paulo
 */
@Service
public class BankSlipService {
    
    @Autowired
    private BankSlipRepository repository;
    
    public void create(BankSlip bankSlip){
        repository.save(bankSlip);
    }
    
}
