package com.bank.loan.loanApplication.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name="payment_status")
    private String paymentStatus;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "link_status")
    private String linkStatus;

    @Column(name = "link_expiry_date")
    private LocalDate linkExpiryDate;

    public Loan() {

    }
    public Loan(String loanNumber, String paymentStatus, BigDecimal amount, String loanType, String linkStatus, LocalDate linkExpiryDate) {
        this.loanNumber = loanNumber;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.loanType = loanType;
        this.linkStatus = linkStatus;
        this.linkExpiryDate = linkExpiryDate;
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

    public LocalDate getLinkExpiryDate() {
        return linkExpiryDate;
    }

    public void setLinkExpiryDate(LocalDate linkExpiryDate) {
        this.linkExpiryDate = linkExpiryDate;
    }
}
