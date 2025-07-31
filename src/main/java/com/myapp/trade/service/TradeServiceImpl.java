package com.myapp.trade.service;

import com.myapp.trade.domain.Trade;
import com.myapp.trade.repository.TradeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {
    private final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    @Cacheable(value = "trades",key = "#tradeId")
    public Optional<Trade> getTradeById(Long id) {
        return tradeRepository.findById(id);
    }

    @Override
    public Trade updateTrade(Long id, Trade tradeDetails) {
        Trade existingTrade = tradeRepository.findById(id).orElseThrow(() -> new RuntimeException("Trade not found with id: " + id));

        existingTrade.setTradeId(tradeDetails.getTradeId());
        existingTrade.setInstrument(tradeDetails.getInstrument());
        existingTrade.setQuantity(tradeDetails.getQuantity());
        existingTrade.setPrice(tradeDetails.getPrice());
        existingTrade.setTradeDate(tradeDetails.getTradeDate());
        existingTrade.setSourceSystem(tradeDetails.getSourceSystem());

        return tradeRepository.save(existingTrade);
    }

    @Override
    @CacheEvict(value = "trades",key = "#tradeId")
    public void deleteTrade(Long id) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new RuntimeException("Trade not found with id: " + id));

        tradeRepository.delete(trade);
    }
}
