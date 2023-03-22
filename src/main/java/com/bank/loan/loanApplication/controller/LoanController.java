package com.bank.loan.loanApplication.controller;

import com.bank.loan.loanApplication.dto.LoanDto;
import com.bank.loan.loanApplication.dto.SendLinkDto;
import com.bank.loan.loanApplication.model.Loan;
import com.bank.loan.loanApplication.service.LoanService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
@CrossOrigin(origins = {"*"})
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;

    private static final Logger logger = LogManager.getLogger(LoanController.class);

    @GetMapping("/getLoanByFilterData")
    public ResponseEntity<List<LoanDto>> getLoanByFilterData(@RequestParam(name = "linkStatus",required = false) String linkStatus, @RequestParam(name = "amount",required = false) BigDecimal amount, @RequestParam(name = "operator",required = false) String operator) {
        logger.log(Level.INFO,"Loan Controller :: Request received to get Loan Data based on filter :: Started");
        List<LoanDto> loanList = loanService.findAllLoanFilterData(linkStatus,amount,operator);
        logger.log(Level.INFO,"Loan Controller :: Request received to get Loan Data based on filter :: Completed");
        return new ResponseEntity<>(loanList, HttpStatus.OK);
    }

    @PostMapping("/sendLink")
    public ResponseEntity<List<LoanDto>> sendPaymentLink(@RequestBody SendLinkDto sendLinkDto) {
        logger.log(Level.INFO,"Loan Controller :: Request received to send payment link :: Started");
        List<LoanDto> loanList = loanService.sendPaymentLink(sendLinkDto.getLoanIds());
        logger.log(Level.INFO,"Loan Controller :: Request received to send payment link :: Completed");
        return new ResponseEntity<>(loanList, HttpStatus.OK);
     }
}
