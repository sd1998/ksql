/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.util;

import io.confluent.ksql.GenericRow;
import io.confluent.ksql.name.ColumnName;
import io.confluent.ksql.schema.ksql.LogicalSchema;
import io.confluent.ksql.schema.ksql.PhysicalSchema;
import io.confluent.ksql.schema.ksql.types.SqlTypes;
import io.confluent.ksql.serde.SerdeOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderDataProvider extends TestDataProvider {

  private static final String namePrefix =
      "ORDER";

  private static final String ksqlSchemaString =
      "(ORDERTIME bigint, ORDERID varchar, ITEMID varchar, ORDERUNITS double, TIMESTAMP varchar, PRICEARRAY array<double>, KEYVALUEMAP map<varchar, double>)";

  private static final String key = "ORDERTIME";

  private static final LogicalSchema schema = LogicalSchema.builder()
      .valueColumn(ColumnName.of("ORDERTIME"), SqlTypes.BIGINT)
      .valueColumn(ColumnName.of("ORDERID"), SqlTypes.STRING)
      .valueColumn(ColumnName.of("ITEMID"), SqlTypes.STRING)
      .valueColumn(ColumnName.of("ORDERUNITS"), SqlTypes.DOUBLE)
      .valueColumn(ColumnName.of("TIMESTAMP"), SqlTypes.STRING)
      .valueColumn(ColumnName.of("PRICEARRAY"), SqlTypes.array(SqlTypes.DOUBLE))
      .valueColumn(ColumnName.of("KEYVALUEMAP"), SqlTypes.map(SqlTypes.DOUBLE))
      .build();

  private static final Map<String, GenericRow> data = buildData();

  public OrderDataProvider() {
    super(namePrefix, ksqlSchemaString, key, PhysicalSchema.from(schema, SerdeOption.none()), data);
  }

  private static Map<String, GenericRow> buildData() {

    final Map<String, Double> mapField = new HashMap<>();
    mapField.put("key1", 1.0);
    mapField.put("key2", 2.0);
    mapField.put("key3", 3.0);

    final Map<String, GenericRow> dataMap = new HashMap<>();
    dataMap.put("1", new GenericRow(Arrays.asList(
        1L,
        "ORDER_1",
        "ITEM_1",
        10.0,
        "2018-01-01",
        Arrays.asList(100.0, 110.99, 90.0 ),
        mapField)));
    dataMap.put("2", new GenericRow(Arrays.asList(
        2L,
        "ORDER_2",
        "ITEM_2",
        20.0,
        "2018-01-02",
        Arrays.asList(10.0, 10.99, 9.0),
        mapField)));

    dataMap.put("3", new GenericRow(Arrays.asList(
        3L,
        "ORDER_3",
        "ITEM_3",
        30.0,
        "2018-01-03",
        Arrays.asList(10.0, 10.99, 91.0),
        mapField)));

    dataMap.put("4", new GenericRow(Arrays.asList(
        4L,
        "ORDER_4",
        "ITEM_4",
        40.0,
        "2018-01-04",
        Arrays.asList(10.0, 140.99, 94.0),
        mapField)));

    dataMap.put("5", new GenericRow(Arrays.asList(
        5L,
        "ORDER_5",
        "ITEM_5",
        50.0,
        "2018-01-05",
        Arrays.asList(160.0, 160.99, 98.0),
        mapField)));

    dataMap.put("6", new GenericRow(Arrays.asList(
        6L,
        "ORDER_6",
        "ITEM_6",
        60.0,
        "2018-01-06",
        Arrays.asList(1000.0, 1100.99, 900.0),
        mapField)));

    dataMap.put("7", new GenericRow(Arrays.asList(
        7L,
        "ORDER_6",
        "ITEM_7",
        70.0,
        "2018-01-07",
        Arrays.asList(1100.0, 1110.99, 190.0),
        mapField)));

    dataMap.put("8", new GenericRow(Arrays.asList(
        8L,
        "ORDER_6",
        "ITEM_8",
        80.0,
        "2018-01-08",
        Arrays.asList(1100.0, 1110.99, 970.0),
        mapField)));

    return dataMap;
  }

}