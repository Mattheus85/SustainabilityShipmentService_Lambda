package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WeightedCostStrategy implements CostStrategy {

    private Map<BigDecimal, CostStrategy> weightedCostStrategyMap;

    private WeightedCostStrategy(Map<BigDecimal, CostStrategy> weightedCostStrategyMap) {
        this.weightedCostStrategyMap = weightedCostStrategyMap;
    }

    private WeightedCostStrategy() { }

    public static Builder builder() {
        return new Builder();
    }

    public Map<BigDecimal, CostStrategy> getWeightedCostStrategyMap() {
        return weightedCostStrategyMap;
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        return new ShipmentCost(shipmentOption, BigDecimal.TEN);
    }

    @Override
    public String toString() {
        return "WeightedCostStrategy{" +
                "weightedCostStrategyMap=" + weightedCostStrategyMap +
                '}';
    }

    public static class Builder {
        private Map<BigDecimal, CostStrategy> weightedCostStrategyMap;

        public Builder addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight) {
            this.weightedCostStrategyMap = new HashMap<>();
            weightedCostStrategyMap.put(weight, costStrategy);
            return this;
        }

        public WeightedCostStrategy build() {
            WeightedCostStrategy weightedCostStrategy = new WeightedCostStrategy(weightedCostStrategyMap);

            weightedCostStrategy.weightedCostStrategyMap = weightedCostStrategyMap;

            return weightedCostStrategy;
        }
    }
}