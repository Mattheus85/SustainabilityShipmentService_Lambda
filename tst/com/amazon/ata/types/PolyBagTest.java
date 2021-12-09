package com.amazon.ata.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolyBagTest {
    private Material packagingMaterial = Material.CORRUGATE;
    private BigDecimal volume = BigDecimal.valueOf(10);

    private Packaging packaging;

    @BeforeEach
    public void setUp() {
        packaging = new PolyBag(packagingMaterial, volume);
    }

    @Test
    public void canFitItem_itemSameSizeAsPolyBag_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withVolume(BigDecimal.valueOf(10))
                .build();

        // WHEN
        boolean canFit = packaging.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item the same size as the package should not fit in the package.");
    }

    @Test
    public void canFitItem_itemSmallerThanPolyBag_doesFit() {
        // GIVEN
        Item item = Item.builder()
                .withVolume(BigDecimal.valueOf(9))
                .build();

        // WHEN
        boolean canFit = packaging.canFitItem(item);

        // THEN
        assertTrue(canFit, "Item smaller than the package should fit in the package.");
    }

    @Test
    public void getMass_calculatesMass_returnsCorrectMass() {
        // GIVEN
        packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(1000));

        // WHEN
        BigDecimal mass = packaging.getMass();

        // THEN
        assertEquals(BigDecimal.valueOf(19.0), mass,
                "Item smaller than the box should fit in the package.");
    }

    @Test
    public void equals_sameObject_isTrue() {
        // GIVEN
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));

        // WHEN
        boolean result = packaging.equals(packaging);

        // THEN
        assertTrue(result, "An object should be equal with itself.");
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        // GIVEN
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));

        // WHEN
        boolean isEqual = packaging.equals(null);

        // THEN
        assertFalse(isEqual, "A Packaging should not be equal with null.");
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        // GIVEN
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));
        Object other = "String type!";

        // WHEN
        boolean isEqual = packaging.equals(other);

        // THEN
        assertFalse(isEqual, "A Packaging should not be equal to an object of a different type.");
    }

    @Test
    public void equals_sameAttributes_returnsTrue() {
        // GIVEN
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));
        Packaging other = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));

        // WHEN
        boolean isEqual = packaging.equals(other);

        // THEN
        assertTrue(isEqual, "Packaging with the same attributes should be equal.");
    }

    @Test
    public void hashCode_equalObjects_equalHash() {
        // GIVEN
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));
        Packaging other = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));

        // WHEN + THEN
        assertEquals(packaging.hashCode(), other.hashCode(), "Equal objects should have equal hashCodes");
    }
}
