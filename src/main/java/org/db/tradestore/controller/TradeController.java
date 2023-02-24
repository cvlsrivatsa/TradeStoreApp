package org.db.tradestore.controller;

import org.db.tradestore.model.TradeRecordDTO;
import org.db.tradestore.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping(value = "/trade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TradeRecordDTO> getTrade(@PathVariable String id){
        return tradeService.getTrades(id);
    }

    @GetMapping(value = "/latest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TradeRecordDTO getLatestTrade(@PathVariable String id){
        return tradeService.getTrade(id);
    }

    @PostMapping(value = "/trade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TradeRecordDTO createNewTrade(@RequestBody TradeRecordDTO dto){
        return tradeService.createNewTrade(dto);
    }
}
