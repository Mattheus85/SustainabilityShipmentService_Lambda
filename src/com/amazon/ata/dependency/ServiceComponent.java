package com.amazon.ata.dependency;

import com.amazon.ata.activity.PrepareShipmentActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { CostStrategyModule.class })
public interface ServiceComponent {
    PrepareShipmentActivity providePrepareShipmentActivity();
}
