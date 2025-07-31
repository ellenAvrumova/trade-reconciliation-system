package com.myapp.trade.service;

import com.myapp.trade.domain.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    Trade createTrade(Trade trade);
    Trade updateTrade(Long id, Trade tradeDetails);
    void deleteTrade(Long id);
    List<Trade> getAllTrades();
    Optional<Trade> getTradeById(Long id);
}
