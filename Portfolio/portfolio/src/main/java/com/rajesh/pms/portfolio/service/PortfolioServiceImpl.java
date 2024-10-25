package com.rajesh.pms.portfolio.service;


import com.rajesh.pms.portfolio.entity.Portfolio;
import com.rajesh.pms.portfolio.repository.PortfolioRepository;
import com.rajesh.pms.portfolio.service.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    // Retrieve all portfolios
    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    // Retrieve a specific portfolio by ID
    public Optional<Portfolio> getPortfolioById(Long id) {
        return portfolioRepository.findById(id);
    }

    // Create a new portfolio
    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    // Update an existing portfolio
//    public Portfolio updatePortfolio(Long id, Portfolio updatedPortfolio) {
//        return portfolioRepository.findById(id)
//                .map(portfolio -> {
//                    portfolio.setName(updatedPortfolio.getName());
//                    portfolio.setDescription(updatedPortfolio.getDescription());
//                    // Add other fields as needed
//                    return portfolioRepository.save(portfolio);
//                })
//                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
//    }

    // Delete a portfolio
    public void deletePortfolio(Long id) {
        portfolioRepository.deleteById(id);
    }
}
