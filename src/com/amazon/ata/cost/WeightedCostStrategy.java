package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

        totalCost = totalCost.add(monetaryCostStrategy.getCost(shipmentOption)
                .getCost().multiply(BigDecimal.valueOf(.8)));

        totalCost = totalCost.add(carbonCostStrategy.getCost(shipmentOption)
                .getCost().multiply(BigDecimal.valueOf(.2)));

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
        private Map<CostStrategy, BigDecimal> costStrategyMap = new HashMap<>();
        private MonetaryCostStrategy monetaryCostStrategy;
        private CarbonCostStrategy carbonCostStrategy;

        /**
         *
         * @param costStrategy - The desired cost strategy
         * @param weight - The desired weight to be given to the cost strategy
         * @return - The Builder object with updated parameters
         */
        public Builder addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight) {
            costStrategyMap.put(costStrategy, weight);
            return this;
        }

        /**
         *
         * @return - Returns a new WeightedCostStrategy object
         */
        public WeightedCostStrategy build() {
            WeightedCostStrategy weightedCostStrategy = new WeightedCostStrategy(
                    new MonetaryCostStrategy(), new CarbonCostStrategy());
            
            weightedCostStrategy.costStrategyMap = costStrategyMap;
            return weightedCostStrategy;
        }
    }
}
