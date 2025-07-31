package com.myapp.trade.repository;

import com.myapp.trade.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
//    List<Trade> findByTradeDate(LocalDateTime tradeDate);
}

