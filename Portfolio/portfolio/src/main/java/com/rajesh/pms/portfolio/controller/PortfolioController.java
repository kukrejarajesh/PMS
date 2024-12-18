package com.rajesh.pms.portfolio.controller;


import com.rajesh.pms.portfolio.entity.Portfolio;
import com.rajesh.pms.portfolio.service.PortfolioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;
    final static Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin() {
    	logger.info("admin endpoint being called");
        return "Hellow Admin";
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloUser() {
    	logger.warn("entering hello user");
    	logger.error("inside user with error logging");
        return "Hello User";
    }


    // Retrieve all portfolios
    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }

    // Retrieve a specific portfolio by ID
    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable Long id) {
        Optional<Portfolio> portfolio = portfolioService.getPortfolioById(id);
        return portfolio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new portfolio
    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolioRequest) {
    	
    	   Portfolio portfolio = new Portfolio();
    	   System.out.println("Portfolio User Id"+portfolioRequest.getUserId());
    	    portfolio.setUserId(portfolioRequest.getUserId()); // Set the userId directly
    	    portfolio.setPortfolioName(portfolioRequest.getPortfolioName());
    	    portfolio.setCreatedAt(LocalDateTime.now());
    	    portfolio.setUpdatedAt(LocalDateTime.now());

    	    
    	
    	
        Portfolio newPortfolio = portfolioService.createPortfolio(portfolio);
        return new ResponseEntity<>(newPortfolio, HttpStatus.CREATED);
    }

    // Update an existing portfolio
    @PutMapping("/{id}")
//    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Long id, @RequestBody Portfolio portfolio) {
//        try {
//            Portfolio updatedPortfolio = portfolioService.updatePortfolio(id, portfolio);
//            return new ResponseEntity<>(updatedPortfolio, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Delete a portfolio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
