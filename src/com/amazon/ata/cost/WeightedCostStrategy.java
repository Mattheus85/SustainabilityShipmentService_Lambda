package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedCostStrategy implements CostStrategy {
    private Map<CostStrategy, BigDecimal> costStrategyMap;
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;

    private WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy,
                                 CarbonCostStrategy carbonCostStrategy) {
        this.monetaryCostStrategy = monetaryCostStrategy;
        this.carbonCostStrategy = carbonCostStrategy;
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal totalCost = BigDecimal.ZERO;
        totalCost = totalCost.add(monetaryCostStrategy.getCost(shipmentOption).getCost().multiply(BigDecimal.valueOf(.8)));
        totalCost = totalCost.add(carbonCostStrategy.getCost(shipmentOption).getCost().multiply(BigDecimal.valueOf(.2)));

        return new ShipmentCost(shipmentOption, totalCost);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<CostStrategy, BigDecimal> costStrategyMap = new HashMap<>();
        private MonetaryCostStrategy monetaryCostStrategy;
        private CarbonCostStrategy carbonCostStrategy;

        public Builder addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight) {
            costStrategyMap.put(costStrategy, weight);
            return this;
        }

        public WeightedCostStrategy build() {
            WeightedCostStrategy weightedCostStrategy = new WeightedCostStrategy(new MonetaryCostStrategy(), new CarbonCostStrategy());
            weightedCostStrategy.costStrategyMap = costStrategyMap;
            return weightedCostStrategy;
        }
    }
}