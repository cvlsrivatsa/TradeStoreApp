package org.db.tradestore.model;

import lombok.Data;

import java.time.Instant;

@Data
public class TradeRecordDTO {
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private Instant maturityDate;
    private Instant createdDate;
    private Boolean isExpired;
}
