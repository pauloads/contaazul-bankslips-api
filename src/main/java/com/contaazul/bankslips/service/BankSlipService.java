package com.contaazul.bankslips.service;

import com.contaazul.bankslips.model.BankSlip;
import com.contaazul.bankslips.model.BankSlipStatus;
import static com.contaazul.bankslips.model.BankSlipStatus.CANCELED;
import static com.contaazul.bankslips.model.BankSlipStatus.PAID;
import com.contaazul.bankslips.repository.BankSlipRepository;
import java.util.List;
import java.util.UUID;
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

    public void create(BankSlip bankSlip) {
        repository.save(bankSlip);
    }

    public List<BankSlip> listAll() {
        return repository.getAllWithoutDetails();
    }
    
    public BankSlip getDetails(UUID id){
        BankSlip detailedBankslip = repository.findById(id).get();
        detailedBankslip.calculateFine();
        return detailedBankslip;
    }

    public void pay(UUID id) {
        BankSlip paidBankslip = repository.findById(id).get();
        paidBankslip.setStatus(PAID);
        repository.save(paidBankslip);
    }

    public void cancel(UUID id) {
        BankSlip canceledbankslip = repository.findById(id).get();
        canceledbankslip.setStatus(CANCELED);
        repository.save(canceledbankslip);
    }

}
