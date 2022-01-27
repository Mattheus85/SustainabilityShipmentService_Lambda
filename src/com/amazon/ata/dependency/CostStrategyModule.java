package com.amazon.ata.dependency;

import com.amazon.ata.cost.CarbonCostStrategy;
import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.cost.WeightedCostStrategy;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.math.BigDecimal;

@Module
public class CostStrategyModule {

    @Singleton
    @Provides
    public CostStrategy provideCostStrategy() {
        return WeightedCostStrategy.builder()
                .addStrategyWithWeight(new CarbonCostStrategy(), BigDecimal.valueOf(.7))
                .addStrategyWithWeight(new MonetaryCostStrategy(), BigDecimal.valueOf(.3))
                .build();
    }
}
