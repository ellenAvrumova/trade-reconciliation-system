package com.myapp.trade.resource;

import com.myapp.trade.domain.Instrument;
import com.myapp.trade.repository.InstrumentRepository;

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

    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentController(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getInstrumentById(@PathVariable String id) {
        Optional<Instrument> instrument = instrumentRepository.findById(id);
        return instrument.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instrument createInstrument(@RequestBody Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instrument> updateInstrument(@PathVariable String id, @RequestBody Instrument instrumentDetails) {
        Optional<Instrument> instrumentOpt = instrumentRepository.findById(id);
        if (instrumentOpt.isPresent()) {
            Instrument instrument = instrumentOpt.get();
            instrument.setSymbol(instrumentDetails.getSymbol());
            instrument.setName(instrumentDetails.getName());
            instrument.setIsin(instrumentDetails.getIsin());
            Instrument updatedInstrument = instrumentRepository.save(instrument);
            return ResponseEntity.ok(updatedInstrument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrument(@PathVariable String id) {
        Optional<Instrument> instrumentOpt = instrumentRepository.findById(id);
        if (instrumentOpt.isPresent()) {
            instrumentRepository.delete(instrumentOpt.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
