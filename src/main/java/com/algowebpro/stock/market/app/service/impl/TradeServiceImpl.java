package com.algowebpro.stock.market.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algowebpro.stock.market.app.entity.Trade;
import com.algowebpro.stock.market.app.repository.TradeRepository;
import com.algowebpro.stock.market.app.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	// Create trade
	@Transactional
	@Override
	public Trade createTrade(Trade trade) {
		trade.setStatus("OPEN");
		Trade savedTrade = tradeRepository.save(trade);
		return savedTrade;

	}

}
