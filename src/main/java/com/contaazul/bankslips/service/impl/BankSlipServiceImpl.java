package com.contaazul.bankslips.service.impl;

import com.contaazul.bankslips.exception.BankskipNotFoundException;
import com.contaazul.bankslips.exception.InvalidUUIDException;
import com.contaazul.bankslips.model.BankSlip;
import static com.contaazul.bankslips.model.BankSlipStatus.CANCELED;
import static com.contaazul.bankslips.model.BankSlipStatus.PAID;
import com.contaazul.bankslips.model.Messages;
import static com.contaazul.bankslips.model.Messages.BANKSLIP_NOT_FOUND;
import static com.contaazul.bankslips.model.Messages.INVALID_ID_PROVIDED;
import com.contaazul.bankslips.repository.BankSlipRepository;
import com.contaazul.bankslips.service.BankSlipService;
import com.contaazul.bankslips.validator.BankslipValidator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paulo
 */
@Service
public class BankSlipServiceImpl implements BankSlipService{

    @Autowired
    private BankSlipRepository repository;

    private BankslipValidator validator = new BankslipValidator();
    
    @Override
    public void create(BankSlip bankSlip) {
        validator.validate(bankSlip);
        repository.save(bankSlip);
    }

    @Override
    public List<BankSlip> listAll() {
        return repository.getAllWithoutDetails();
    }
    
    @Override
    public BankSlip getDetails(String id){
        BankSlip detailedBankslip;
        try{
            UUID.fromString(id);
            detailedBankslip = repository.findById(id).get();
        }catch(IllegalArgumentException ex){
            throw new InvalidUUIDException(INVALID_ID_PROVIDED.getMessage());
        }catch(NoSuchElementException ex){
            throw new BankskipNotFoundException(BANKSLIP_NOT_FOUND.getMessage());
        }
        detailedBankslip.calculateFine();
        return detailedBankslip;
    }

    @Override
    public void pay(String id) {
        BankSlip paidBankslip = repository.findById(id).get(); 
        paidBankslip.setStatus(PAID);
        repository.save(paidBankslip);
    }

    @Override
    public void cancel(String id) {
        BankSlip canceledbankslip = repository.findById(id).get();
        canceledbankslip.setStatus(CANCELED);
        repository.save(canceledbankslip);
    }

}
