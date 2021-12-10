package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WeightedCostStrategy implements CostStrategy {
    private Map<BigDecimal, CostStrategy> costStrategyMap;

    private WeightedCostStrategy(Map<BigDecimal, CostStrategy> costStrategyMap) {
        this.costStrategyMap = costStrategyMap;
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Map.Entry<BigDecimal, CostStrategy> anEntry : costStrategyMap.entrySet()) {
            totalCost = totalCost.add(anEntry.getValue().getCost(shipmentOption).getCost().multiply(anEntry.getKey()));
        }
        return new ShipmentCost(shipmentOption, totalCost);
    }

    /**
     * @return - returns a new Builder object
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Nested Builder class.
     */
    public static class Builder {
        private Map<BigDecimal, CostStrategy> costStrategyMap = new HashMap<>();

        /**
         * @param costStrategy - The desired cost strategy
         * @param weight - The desired weight to be given to the cost strategy
         * @return - The Builder object with updated parameters
         */
        public Builder addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight) {
            costStrategyMap.put(weight, costStrategy);
            return this;
        }

        /**
         * @return - Returns a new WeightedCostStrategy object
         */
        public WeightedCostStrategy build() {
            return new WeightedCostStrategy(costStrategyMap);
        }
    }
}
