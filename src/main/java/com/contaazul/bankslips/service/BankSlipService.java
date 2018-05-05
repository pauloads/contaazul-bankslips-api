package com.contaazul.bankslips.service;

import com.contaazul.bankslips.model.BankSlip;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Paulo
 */
public interface BankSlipService {

    public void create(BankSlip bankSlip);

    public List<BankSlip> listAll();

    public BankSlip getDetails(String id);

    public void pay(String id);

    public void cancel(String id);
}
