package com.contaazul.bankslips.model;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Paulo
 */
public class BankSlipTest {

    @Test
    public void shouldCalculateFineForBankSlipDueMoreThanTenDays() {

        LocalDate fifteenDaysAgo = LocalDate.now().minusDays(15);
        BankSlip bankSlip = new BankSlip();
        bankSlip.setTotalInCents(100000L);
        bankSlip.setDueDate(fifteenDaysAgo);

        bankSlip.calculateFine();

        assertEquals(new Long(15000), bankSlip.getFine());

    }

    @Test
    public void shouldCalculateFineForBankSlipDueLessThanTenDays() {

        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);
        BankSlip bankSlip = new BankSlip();
        bankSlip.setTotalInCents(100000L);
        bankSlip.setDueDate(threeDaysAgo);

        bankSlip.calculateFine();

        assertEquals(new Long(1500), bankSlip.getFine());

    }

    public void shouldSetFineToZeroWhenBankslipIsNotDue() {
        LocalDate threeDaysLater = LocalDate.now().plusDays(3);
        BankSlip bankSlip = new BankSlip();
        bankSlip.setTotalInCents(100000L);
        bankSlip.setDueDate(threeDaysLater);

        bankSlip.calculateFine();

        assertEquals(new Long(0), bankSlip.getFine());

    }
}
