package org.db.tradestore.store;

import org.db.tradestore.model.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TradeRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public void save(final TradeRecord order) {
        try {
            DynamoDbTable<TradeRecord> orderTable = getTable();
            orderTable.putItem(order);
        } catch(DynamoDbException e) {
            System.err.println(e.getMessage());
        }
    }

    public TradeRecord getTradeRecord(final String tradeId, final Integer version) {
        TradeRecord record = null;
        try {
            DynamoDbTable<TradeRecord> orderTable = getTable();
            Key key = Key.builder()
                    .partitionValue(tradeId)
                    .sortValue(version)
                    .build();
            record = orderTable.getItem(key);
        } catch(DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return record;
    }

    public PageIterable<TradeRecord> getTradeRecords(final String tradeId) {
        try {
            DynamoDbTable<TradeRecord> orderTable = getTable();
            QueryConditional queryConditional = QueryConditional
                    .keyEqualTo(Key.builder()
                            .partitionValue(tradeId)
                            .build());
            return orderTable.query(r -> r.queryConditional(queryConditional));
        } catch(DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public PageIterable<TradeRecord> findTradesByVersion(final String tradeId,
                                                         final Integer version) {
        DynamoDbTable<TradeRecord> orderTable = getTable();
        AttributeValue attributeValue = AttributeValue.builder()
                .n(String.valueOf(version))
                .build();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":value", attributeValue);
        Expression expression = Expression.builder()
                .expression("version > :value")
                .expressionValues(expressionValues)
                .build();
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder()
                        .partitionValue(tradeId)
                        .build());
        PageIterable<TradeRecord> results = orderTable
                .query(r -> r.queryConditional(queryConditional)
                        .filterExpression(expression));
        return results;
    }

    private DynamoDbTable<TradeRecord> getTable() {
        DynamoDbTable<TradeRecord> orderTable = dynamoDbEnhancedClient.table("TradeRecordTable",
                TableSchema.fromBean(TradeRecord.class));
        return orderTable;
    }

}
