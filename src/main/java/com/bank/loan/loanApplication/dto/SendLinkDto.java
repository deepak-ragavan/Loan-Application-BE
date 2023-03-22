package com.bank.loan.loanApplication.dto;

import java.util.List;

public class SendLinkDto {

    private List<Long> loanIds;

    public List<Long> getLoanIds() {
        return loanIds;
    }

    public void setLoanIds(List<Long> loanIds) {
        this.loanIds = loanIds;
    }
}
