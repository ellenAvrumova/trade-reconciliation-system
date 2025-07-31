package com.myapp.trade.repository;

import com.myapp.trade.domain.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
//    List<Instrument> findInstrumentById(long id);
}
