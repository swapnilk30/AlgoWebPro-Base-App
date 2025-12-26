package com.algowebpro.stock.market.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradeId;

	@Column(nullable = false)
	private LocalDateTime tradeDate;

	@Column(nullable = false)
	private String symbol; // e.g., NIFTY, BANKNIFTY

	@Column(nullable = false)
	private Integer strikePrice;

	@Column(nullable = false)
	private String optionType; // CE or PE

	@Column(nullable = false)
	private String action; // BUY or SELL

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Double entryPrice;

	private Double exitPrice;

	private Double spotPrice;

	@Column(nullable = false)
	private String status; // OPEN, CLOSED

	private Double pnl;

	@Column(length = 1000)
	private String notes;

	@Column(length = 500)
	private String strategy; // e.g., Iron Condor, Butterfly, Straddle

	private String expiryDate;

	private LocalDateTime exitDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
		if (tradeDate == null) {
			tradeDate = LocalDateTime.now();
		}
		if (status == null) {
			status = "OPEN";
		}
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	// Calculate P&L
	public void calculatePnL() {
		if (exitPrice != null) {
			double priceDiff = exitPrice - entryPrice;
			if ("SELL".equals(action)) {
				priceDiff = -priceDiff;
			}
			this.pnl = priceDiff * quantity;
		}
	}

}
