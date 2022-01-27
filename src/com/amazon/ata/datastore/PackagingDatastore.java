package com.amazon.ata.datastore;


import com.amazon.ata.types.Box;
import com.amazon.ata.types.FcPackagingOption;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.PolyBag;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    @Inject
    public PackagingDatastore() {}

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOption("IND1", "10", "10", "10"),
            createFcPackagingOption("IND1", BigDecimal.valueOf(2000)),
            createFcPackagingOption("IND1", BigDecimal.valueOf(5000)),
            createFcPackagingOption("ABE2", "20", "20", "20"),
            createFcPackagingOption("ABE2", "40", "40", "40"),
            createFcPackagingOption("ABE2", BigDecimal.valueOf(2000)),
            createFcPackagingOption("ABE2", BigDecimal.valueOf(6000)),
            createFcPackagingOption("YOW4", "10", "10", "10"),
            createFcPackagingOption("YOW4", "20", "20", "20"),
            createFcPackagingOption("YOW4", "60", "60", "60"),
            createFcPackagingOption("YOW4", BigDecimal.valueOf(2000)),
            createFcPackagingOption("YOW4", BigDecimal.valueOf(5000)),
            createFcPackagingOption("YOW4", BigDecimal.valueOf(5000)),
            createFcPackagingOption("YOW4", BigDecimal.valueOf(10000)),
            createFcPackagingOption("IAD2", "20", "20", "20"),
            createFcPackagingOption("IAD2", "20", "20", "20"),
            createFcPackagingOption("IAD2", BigDecimal.valueOf(2000)),
            createFcPackagingOption("IAD2", BigDecimal.valueOf(5000)),
            createFcPackagingOption("IAD2", BigDecimal.valueOf(10000)),
            createFcPackagingOption("PDX1", "40", "40", "40"),
            createFcPackagingOption("PDX1", "60", "60", "60"),
            createFcPackagingOption("PDX1", "60", "60", "60"),
            createFcPackagingOption("PDX1", BigDecimal.valueOf(5000)),
            createFcPackagingOption("PDX1", BigDecimal.valueOf(10000))
    );

    /**
     * Create fulfillment center box packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(Material.CORRUGATE, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    /**
     * Create fulfillment center polybag packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode,
                                                      BigDecimal volume) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, volume);

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
