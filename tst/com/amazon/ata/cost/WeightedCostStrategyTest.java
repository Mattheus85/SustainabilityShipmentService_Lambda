package com.amazon.ata.cost;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeightedCostStrategyTest {
    private final Packaging BOX_10x10x20 =
            new Box(Material.CORRUGATE, BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(20));

    private WeightedCostStrategy weightedStrategy;
    private final CostStrategy monetaryStrategy = new MonetaryCostStrategy();
    private final CostStrategy carbonStrategy = new CarbonCostStrategy();
    private final BigDecimal monetaryWeight = BigDecimal.valueOf(Math.random());
    private final BigDecimal carbonWeight = BigDecimal.valueOf(Math.random());

    @Test
    void getCost_withSingleStrategy_returnsCorrectCost() {
        // GIVEN
        weightedStrategy = WeightedCostStrategy.builder()
                .addStrategyWithWeight(monetaryStrategy, monetaryWeight)
                .build();

        ShipmentOption shipmentOption = ShipmentOption.builder()
                .withPackaging(BOX_10x10x20)
                .build();

        BigDecimal expectedCostWithWeights = monetaryStrategy.getCost(shipmentOption).getCost().multiply(monetaryWeight);

        //WHEN
        ShipmentCost shipmentCost = weightedStrategy.getCost(shipmentOption);

        //THEN
        assertTrue(expectedCostWithWeights.compareTo(shipmentCost.getCost()) == 0,
                "Incorrect weighted cost calculation for a box with dimensions 10x10x20.");
    }

    @Test
    void getCost_withMultipleStrategies_returnsCorrectCost() {
        // GIVEN
        weightedStrategy = WeightedCostStrategy.builder()
                .addStrategyWithWeight(monetaryStrategy, monetaryWeight)
                .addStrategyWithWeight(carbonStrategy, carbonWeight)
                .build();

        ShipmentOption shipmentOption = ShipmentOption.builder()
                .withPackaging(BOX_10x10x20)
                .build();

        BigDecimal expectedCostWithWeights = monetaryStrategy.getCost(shipmentOption).getCost().multiply(monetaryWeight)
                .add(carbonStrategy.getCost(shipmentOption).getCost().multiply(carbonWeight));

        //WHEN
        ShipmentCost shipmentCost = weightedStrategy.getCost(shipmentOption);

        //THEN
        assertTrue(expectedCostWithWeights.compareTo(shipmentCost.getCost()) == 0,
                "Incorrect weighted cost calculation for a box with dimensions 10x10x20.");
    }

}
