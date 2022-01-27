package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a weighted cost strategy so a user can specify which cost strategies to use and
 * what weight to assign to each in determining the total shipment cost.
 *
 * Construct an WeightedCostStrategy via the {@code WeightedCostStrategy.builder()...build();} pattern,
 * for example:
 *
 * <pre>{@code
 *   WeightedCostStrategy weightedCostStrategy = WeightedCostStrategy.builder()
 *                      .addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight)
 *                      ...
 *                      .addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight)
 *                      .build();
 * }</pre>
 *
 * Explanation of fields:
 * costStrategyMap - A map which links specific calculation weights to their cost strategies
 */
public class WeightedCostStrategy implements CostStrategy {
    private final Map<CostStrategy, BigDecimal> costStrategyMap;

    /**
     * WeightedCostStrategy constructor is private because it's the Builder's job to
     * instantiate the WeightedCostStrategy object.
     *
     * @param costStrategyMap - Map of cost strategies and their respective weights
     */
    private WeightedCostStrategy(Map<CostStrategy, BigDecimal> costStrategyMap) {
        this.costStrategyMap = costStrategyMap;
    }

    /**
     * @param shipmentOption a shipment option with packaging
     * @return returns a ShipmentOption with the sum of all cost strategies calculated using their
     * respective weights
     */
    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Map.Entry<CostStrategy, BigDecimal> anEntry : costStrategyMap.entrySet()) {
            totalCost = totalCost.add(anEntry.getKey().getCost(shipmentOption)
                    .getCost().multiply(anEntry.getValue()));
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
     * The costStrategyMap is empty until populated with an addStrategyWithWeight() call
     */
    public static class Builder {
        private final Map<CostStrategy, BigDecimal> costStrategyMap = new HashMap<>();

        /**
         * @param costStrategy - The desired cost strategy
         * @param weight - The desired weight to be given to the cost strategy
         * @return - The Builder object with updated parameters
         */
        public Builder addStrategyWithWeight(CostStrategy costStrategy, BigDecimal weight) {
            costStrategyMap.put(costStrategy, weight);
            return this;
        }

        /**
         * @return - Returns a new WeightedCostStrategy object with an optionally populated Map
         */
        public WeightedCostStrategy build() {
            return new WeightedCostStrategy(costStrategyMap);
        }
    }
}
