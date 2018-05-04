package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.model.BankSlip;
import com.contaazul.bankslips.service.BankSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paulo
 */
@RestController
@RequestMapping("/rest/bankslips")
public class BankSlipController {
    
    @Autowired
    private BankSlipService service;
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody BankSlip bankSlip){
        service.create(bankSlip);
        return new ResponseEntity("Bankslip created", HttpStatus.CREATED);
    }
}
