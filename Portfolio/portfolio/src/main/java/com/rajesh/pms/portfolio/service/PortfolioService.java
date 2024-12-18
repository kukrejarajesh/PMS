package com.rajesh.pms.portfolio.service;


import java.util.List;
import java.util.Optional;

import com.rajesh.pms.portfolio.entity.Portfolio;

public interface PortfolioService {

    // Retrieve all portfolios
    List<Portfolio> getAllPortfolios();

    // Retrieve a specific portfolio by ID
    Optional<Portfolio> getPortfolioById(Long id);

    // Create a new portfolio
    Portfolio createPortfolio(Portfolio portfolio);

    // Update an existing portfolio
//    Portfolio updatePortfolio(Long id, Portfolio updatedPortfolio);

    // Delete a portfolio
    void deletePortfolio(Long id);
}
