package com.myapp.trade.resource;

import com.myapp.trade.domain.Trade;
import com.myapp.trade.repository.TradeRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trades")
@Tag(name = "Trades")
public class TradeController {

    private final TradeRepository tradeRepository;

    @Autowired
    public TradeController(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    @GetMapping("/tradeId/{tradeId}")
    public List<Trade> getTradesByTradeId(@PathVariable String tradeId) {
        return tradeRepository.findByTradeId(tradeId);
    }

    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        return tradeRepository.save(trade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @RequestBody Trade tradeDetails) {
        Optional<Trade> tradeOpt = tradeRepository.findById(id);
        if (tradeOpt.isPresent()) {
            Trade trade = tradeOpt.get();
            trade.setTradeId(tradeDetails.getTradeId());
            trade.setInstrument(tradeDetails.getInstrument());
            trade.setQuantity(tradeDetails.getQuantity());
            trade.setPrice(tradeDetails.getPrice());
            trade.setTradeDate(tradeDetails.getTradeDate());
            trade.setSourceSystem(tradeDetails.getSourceSystem());
            Trade updatedTrade = tradeRepository.save(trade);
            return ResponseEntity.ok(updatedTrade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        Optional<Trade> tradeOpt = tradeRepository.findById(id);
        if (tradeOpt.isPresent()) {
            tradeRepository.delete(tradeOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
