package com.bank.loan.loanApplication.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanDto {
    private Long loanId;
    private String loanNumber;
    private String paymentStatus;
    private BigDecimal amount;
    private String loanType;
    private String linkStatus;

    public LoanDto(Long loanId, String loanNumber, String paymentStatus, BigDecimal amount, String loanType, String linkStatus) {
        this.loanId = loanId;
        this.loanNumber = loanNumber;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.loanType = loanType;
        this.linkStatus = linkStatus;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(String linkStatus) {
        this.linkStatus = linkStatus;
    }

}
