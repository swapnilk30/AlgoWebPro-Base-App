package com.algowebpro.stock.market.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algowebpro.stock.market.app.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

}
