package org.db.tradestore.service;

import org.db.tradestore.model.TradeRecord;
import org.db.tradestore.model.TradeRecordDTO;
import org.db.tradestore.store.TradeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<TradeRecordDTO> getTrades(String tradeId) {
        return tradeRepository.getTradeRecords(tradeId).items()
                .stream()
                .map(o -> { TradeRecordDTO dto = new TradeRecordDTO();
                    BeanUtils.copyProperties(o, dto);
                    return dto; } )
                .collect(Collectors.toList());
    }

    public TradeRecordDTO getTrade(String id) {
        TradeRecordDTO response = new TradeRecordDTO();
        return response;
    }

    public TradeRecordDTO createNewTrade(TradeRecordDTO dto) {
        TradeRecord record = new TradeRecord();
        BeanUtils.copyProperties(dto, record);
        LocalDate localDate = LocalDate.parse("2020-04-07");
        LocalDateTime localDateTime = localDate.atStartOfDay();
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        record.setMaturityDate(instant);
        record.setCreatedDate(instant);
        tradeRepository.save(record);
        TradeRecordDTO response = new TradeRecordDTO();
        BeanUtils.copyProperties(record, response);
        return response;
    }
}
