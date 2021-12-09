package com.amazon.ata.cost;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarbonCostStrategy implements CostStrategy {

    private static final BigDecimal CORRUGATE_SUSTAINABILITY_INDEX = BigDecimal.valueOf(0.017);
    private static final BigDecimal POLYBAG_SUSTAINABILITY_INDEX = BigDecimal.valueOf(0.012);
    private final Map<Material, BigDecimal> carbonCostPerGram;

    /**
     * Initializes a CarbonCostStrategy.
     */
    public CarbonCostStrategy() {
        carbonCostPerGram = new HashMap<>();
        carbonCostPerGram.put(Material.CORRUGATE, CORRUGATE_SUSTAINABILITY_INDEX);
        carbonCostPerGram.put(Material.LAMINATED_PLASTIC, POLYBAG_SUSTAINABILITY_INDEX);
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal carbonCost = this.carbonCostPerGram.get(packaging.getMaterial());

        BigDecimal cost = packaging.getMass().multiply(carbonCost);

        return new ShipmentCost(shipmentOption, cost);
    }
}
