package com.amazon.ata.datastore;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackagingDatastoreTest {

    FulfillmentCenter ind1 = new FulfillmentCenter("IND1");
    FulfillmentCenter abe2 = new FulfillmentCenter("ABE2");
    FulfillmentCenter yow4 = new FulfillmentCenter("YOW4");
    FulfillmentCenter iad2 = new FulfillmentCenter("IAD2");
    FulfillmentCenter pdx1 = new FulfillmentCenter("PDX1");

    Packaging package2000CC = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(2000));
    Packaging package5000CC = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(5000));
    Packaging package6000CC = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(6000));
    Packaging package10000CC = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10000));

    Packaging package10Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10));

    Packaging package20Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20));

    Packaging package40Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40));

    Packaging package60Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60));

    FcPackagingOption ind1_10Cm = new FcPackagingOption(ind1, package10Cm);
    FcPackagingOption ind1_2000CC = new FcPackagingOption(ind1, package2000CC);
    FcPackagingOption ind1_5000CC = new FcPackagingOption(ind1, package5000CC);
    FcPackagingOption abe2_20Cm = new FcPackagingOption(abe2, package20Cm);
    FcPackagingOption abe2_40Cm = new FcPackagingOption(abe2, package40Cm);
    FcPackagingOption abe2_2000CC = new FcPackagingOption(abe2, package2000CC);
    FcPackagingOption abe2_6000CC = new FcPackagingOption(abe2, package6000CC);
    FcPackagingOption yow4_10Cm = new FcPackagingOption(yow4, package10Cm);
    FcPackagingOption yow4_20Cm = new FcPackagingOption(yow4, package20Cm);
    FcPackagingOption yow4_60Cm = new FcPackagingOption(yow4, package60Cm);
    FcPackagingOption yow4_2000CC = new FcPackagingOption(yow4, package2000CC);
    FcPackagingOption yow4_5000CC = new FcPackagingOption(yow4, package5000CC);
    FcPackagingOption yow4_10000CC = new FcPackagingOption(yow4, package10000CC);
    FcPackagingOption iad2_20Cm = new FcPackagingOption(iad2, package20Cm);
    FcPackagingOption iad2_2000CC = new FcPackagingOption(iad2, package2000CC);
    FcPackagingOption iad2_5000CC = new FcPackagingOption(iad2, package5000CC);
    FcPackagingOption iad2_10000CC = new FcPackagingOption(iad2, package10000CC);
    FcPackagingOption pdx1_40Cm = new FcPackagingOption(pdx1, package40Cm);
    FcPackagingOption pdx1_60Cm = new FcPackagingOption(pdx1, package60Cm);
    FcPackagingOption pdx1_5000CC = new FcPackagingOption(pdx1, package5000CC);
    FcPackagingOption pdx1_10000CC = new FcPackagingOption(pdx1, package10000CC);


    @Test
    public void getFcPackagingOptions_get_returnAllOptions() {
        // GIVEN
        PackagingDatastore packagingDatastore = new PackagingDatastore();
        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(   ind1_10Cm,
                                                                            ind1_2000CC,
                                                                            ind1_5000CC,
                                                                            abe2_20Cm,
                                                                            abe2_40Cm,
                                                                            abe2_2000CC,
                                                                            abe2_6000CC,
                                                                            yow4_10Cm,
                                                                            yow4_20Cm,
                                                                            yow4_60Cm,
                                                                            yow4_2000CC,
                                                                            yow4_5000CC,
                                                                            yow4_5000CC,
                                                                            yow4_10000CC,
                                                                            iad2_20Cm,
                                                                            iad2_20Cm,
                                                                            iad2_2000CC,
                                                                            iad2_5000CC,
                                                                            iad2_10000CC,
                                                                            pdx1_40Cm,
                                                                            pdx1_60Cm,
                                                                            pdx1_60Cm,
                                                                            pdx1_5000CC,
                                                                            pdx1_10000CC);

        // WHEN
        List<FcPackagingOption> fcPackagingOptions = packagingDatastore.getFcPackagingOptions();

        // THEN
        assertEquals(expectedPackagingOptions.size(), fcPackagingOptions.size(),
                String.format("There should be %s FC/Packaging pairs.", expectedPackagingOptions.size()));
        for (FcPackagingOption expectedPackagingOption : expectedPackagingOptions) {
            assertTrue(fcPackagingOptions.contains(expectedPackagingOption), String.format("expected packaging " +
                            "options from PackagingDatastore to contain %s package in fc %s",
                    expectedPackagingOption.getPackaging(),
                    expectedPackagingOption.getFulfillmentCenter().getFcCode()));
        }
        assertTrue(true, "getFcPackagingOptions contained all of the expected options.");
    }
}
