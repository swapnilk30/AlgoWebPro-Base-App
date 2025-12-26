package com.algowebpro.stock.market.app.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algowebpro.stock.market.app.entity.Trade;
import com.algowebpro.stock.market.app.service.TradeService;

@RequestMapping("/stock-app")
public class TradeViewController {
	
	@Autowired
    private TradeService tradeService;

	// All trades page
	@GetMapping("/trades")
	public String allTrades(Model model) {

		return "trades";
	}

	// New trade form
	@GetMapping("/trades/new")
	public String newTradeForm(Model model) {
		model.addAttribute("trade", new Trade());
		model.addAttribute("action", "create");
		return "trade-form";
	}

	// Create trade
	@PostMapping("/trades")
	public String createTrade(@ModelAttribute Trade trade, RedirectAttributes redirectAttributes) {
		try {
			tradeService.createTrade(trade);
			redirectAttributes.addFlashAttribute("success", "Trade created successfully!");
			return "redirect:/";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Error creating trade: " + e.getMessage());
			return "redirect:/trades/new";
		}
	}

}
