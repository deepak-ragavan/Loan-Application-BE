package com.bank.loan.loanApplication.service;
import com.bank.loan.loanApplication.dto.LoanDto;
import com.bank.loan.loanApplication.exception.LoanNotFoundException;
import com.bank.loan.loanApplication.model.Loan;
import com.bank.loan.loanApplication.repository.LoanRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    private static final Logger logger = LogManager.getLogger(LoanService.class);

    public List<Loan> findAllDetails() {
        return loanRepository.findAll();
    }

    public List<LoanDto> findAllLoanFilterData(String linkStatus,BigDecimal amount,String operator) {
        logger.log(Level.INFO,"Loan Service :: Request received to get Loan Data based on filter :: Started");
        List<Loan> loanList = new ArrayList<>();
        loanList = loanRepository.findAll();
        loanList = checkLinkExpiredOrNot(loanList);
        if(linkStatus != null && !linkStatus.isEmpty() && !linkStatus.equalsIgnoreCase("all")) {
            loanList = loanList.stream().filter(loan -> loan.getLinkStatus().equalsIgnoreCase(linkStatus)).collect(Collectors.toList());
        }

        if(amount != null && operator != null) {
            loanList = getLoanDetailsByAmount(amount,operator,loanList);
        }
        logger.log(Level.INFO,"Loan Service :: Request received to get Loan Data based on filter :: Completed");
        return mapEntityTODTO(loanList);
    }

    private List<Loan> checkLinkExpiredOrNot(List<Loan> loanList) {
        logger.log(Level.INFO,"Loan Service :: Request received to check the link data expired or not :: Started");
        List<Loan> changedList = new ArrayList<>();
        for(Loan loan:loanList) {
            if(loan.getLinkExpiryDate() != null && loan.getLinkStatus().equals("link sent")
                    && loan.getLinkExpiryDate().isBefore(LocalDate.now())) {
                loan.setLinkStatus("send link");
                loan.setLinkExpiryDate(null);
                changedList.add(loanRepository.save(loan));
            }
        }
        logger.log(Level.INFO,"Loan Service :: Request received to check the link data expired or not :: Completed");
        return changedList.size()>0?changedList:loanList;
    }

    private List<Loan> getLoanDetailsByAmount(BigDecimal amount, String operator, List<Loan> loanList) {
        logger.log(Level.INFO,"Loan Service :: Request received to filter data by amount :: Started");
        List<Loan> loanListByAmount = new ArrayList<>();
        switch (operator) {
            case ">":
                loanListByAmount = loanList.stream().filter(loan -> loan.getAmount().compareTo(amount)>0).collect(Collectors.toList());
                break;
            case "<":
                loanListByAmount = loanList.stream().filter(loan -> loan.getAmount().compareTo(amount)<0).collect(Collectors.toList());
                break;
            case ">=":
                loanListByAmount = loanList.stream().filter(loan -> loan.getAmount().compareTo(amount)>=0).collect(Collectors.toList());
                break;
            case "<=":
                loanListByAmount = loanList.stream().filter(loan -> loan.getAmount().compareTo(amount)<=0).collect(Collectors.toList());
                break;
            case "==":
                loanListByAmount = loanList.stream().filter(loan -> loan.getAmount().compareTo(amount)==0).collect(Collectors.toList());
                break;
        }
        logger.log(Level.INFO,"Loan Service :: Request received to filter data by amount :: Completed");
        return loanListByAmount;
    }

    public List<LoanDto> sendPaymentLink(List<Long> loanIds) throws LoanNotFoundException {
        logger.log(Level.INFO,"Loan Service :: Request received to send payment link :: Started");
        List<Loan> result = new ArrayList<>();
        for(Long id:loanIds) {
            Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException());
            loan.setLinkStatus("link sent");
            loan.setLinkExpiryDate(LocalDate.now());
            Loan savedLoan = loanRepository.save(loan);
            result.add(savedLoan);
        }
        logger.log(Level.INFO,"Loan Service :: Request received to send payment link :: Completed");
        return mapEntityTODTO(loanRepository.findAll());
    }

    private List<LoanDto> mapEntityTODTO(List<Loan> loanList) {
        return loanList.stream().map(loan -> {
            return new LoanDto(loan.getLoanId(),loan.getLoanNumber(),loan.getPaymentStatus(),
                    loan.getAmount(),loan.getLoanType(),loan.getLinkStatus());
        }).collect(Collectors.toList());
    }
}
