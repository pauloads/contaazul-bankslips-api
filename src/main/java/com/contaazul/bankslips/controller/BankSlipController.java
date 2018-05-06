package com.contaazul.bankslips.controller;

import com.contaazul.bankslips.model.BankSlip;
import static com.contaazul.bankslips.model.Messages.BANKSLIP_CANCELED;
import static com.contaazul.bankslips.model.Messages.BANKSLIP_CREATED;
import static com.contaazul.bankslips.model.Messages.BANKSLIP_PAID;
import com.contaazul.bankslips.response.Response;
import com.contaazul.bankslips.service.BankSlipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<String> create(@RequestBody BankSlip bankSlip) {
        service.create(bankSlip);
        return new ResponseEntity(new Response().withMessage(BANKSLIP_CREATED.getMessage()), CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<BankSlip>> list() {
        List<BankSlip> allBankslips = service.listAll();
        return new ResponseEntity<>(allBankslips, OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BankSlip> getDetails(@PathVariable("id") String id) {
        BankSlip detailedBankSlip = service.getDetails(id);
        return new ResponseEntity<>(detailedBankSlip, OK);
    }

    @PutMapping("/{id}/pay")
    @ResponseBody
    public ResponseEntity<?> pay(@PathVariable("id") String id) {
        service.pay(id);
        return new ResponseEntity<>(new Response().withMessage(BANKSLIP_PAID.getMessage()), OK);
    }

    @DeleteMapping("/{id}/cancel")
    @ResponseBody
    public ResponseEntity<?> cancel(@PathVariable("id") String id) {
        service.cancel(id);
        return new ResponseEntity<>(new Response().withMessage(BANKSLIP_CANCELED.getMessage()), OK);
    }
}
