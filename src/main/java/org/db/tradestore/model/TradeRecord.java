package org.db.tradestore.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;

@DynamoDbBean
@Setter
@NoArgsConstructor
public class TradeRecord {
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private Instant maturityDate;
    private Instant createdDate;
    private Boolean isExpired;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("TradeId")
    public String getTradeId() {
        return this.tradeId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("Version")
    public Integer getVersion() {
        return this.version;
    }

    @DynamoDbAttribute("CounterPartyId")
    public String getCounterPartyId() {
        return this.counterPartyId;
    }

    @DynamoDbAttribute("BookId")
    public String getBookId() {
        return this.bookId;
    }

    @DynamoDbAttribute("MaturityDate")
    public Instant getMaturityDate() {
        return this.maturityDate;
    }

    @DynamoDbAttribute("CreatedDate")
    public Instant getCreatedDate() {
        return this.createdDate;
    }

    @DynamoDbAttribute("Expired")
    public boolean getExpired() {
        return this.isExpired;
    }

}
