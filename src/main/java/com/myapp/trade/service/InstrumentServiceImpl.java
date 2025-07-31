package com.myapp.trade.service;

import com.myapp.trade.domain.Instrument;
import com.myapp.trade.repository.InstrumentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Instrument createInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public Instrument updateInstrument(long id, Instrument instrumentDetails) {
        Instrument existingInstrument = instrumentRepository.findById(id).orElseThrow(() -> new RuntimeException("Instrument not found with id: " + id));

        existingInstrument.setSymbol(instrumentDetails.getSymbol());
        existingInstrument.setName(instrumentDetails.getName());
        existingInstrument.setIsin(instrumentDetails.getIsin());

        return instrumentRepository.save(existingInstrument);
    }

    @Override
    @CacheEvict(value = "instruments",key = "#instrumentId")
    public void deleteInstrument(long id) {
        Instrument instrument = instrumentRepository.findById(id).orElseThrow(() -> new RuntimeException("Instrument not found with id: " + id));
        instrumentRepository.delete(instrument);
    }

    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    @Cacheable(value = "instruments",key = "#instrumentId")
    public Optional<Instrument> getInstrumentById(long id) {
        return instrumentRepository.findById(id);
    }
}
