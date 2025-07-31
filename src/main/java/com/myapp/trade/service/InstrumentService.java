package com.myapp.trade.service;

import com.myapp.trade.domain.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentService {
    Instrument createInstrument(Instrument instrument);
    Instrument updateInstrument(long id, Instrument instrumentDetails);
    void deleteInstrument(long id);
    List<Instrument> getAllInstruments();
    Optional<Instrument> getInstrumentById(long id);
}
