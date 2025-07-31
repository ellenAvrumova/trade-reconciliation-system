package com.myapp.trade.resource;

import com.myapp.trade.domain.Instrument;

import com.myapp.trade.service.InstrumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instruments")
@Tag(name = "Instruments")
public class InstrumentController {

    private final InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public List<Instrument> getAllInstruments() {
        return instrumentService.getAllInstruments();
    }

    @GetMapping("/{id}")
    public Instrument getInstrumentById(Long id) {
        return instrumentService.getInstrumentById(id).get();
    }

    @PostMapping
    public ResponseEntity<Instrument> createInstrument(@RequestBody Instrument instrument) {
        Instrument createdInstrument = instrumentService.createInstrument(instrument);
        return ResponseEntity.ok(createdInstrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrument> updateInstrument(@PathVariable Long id, @RequestBody Instrument instrumentDetails) {
        Instrument updatedInstrument = instrumentService.updateInstrument(id, instrumentDetails);
        return ResponseEntity.ok(updatedInstrument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrument(@PathVariable Long id) {
        instrumentService.deleteInstrument(id);
        return ResponseEntity.noContent().build();
    }
}
