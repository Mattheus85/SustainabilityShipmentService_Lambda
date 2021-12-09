package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging {

    private BigDecimal volume;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     * @param volume - the Volume of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    /**
     * Returns whether the given item will fit in this packaging.
     *
     * @param item the item to test fit for
     * @return whether the item will fit in this packaging
     */
    public boolean canFitItem(Item item) {
        return this.volume.compareTo(item.getVolume()) > 0;
    }

    /**
     * Returns an approximation of the mass of the packaging in grams.
     * @return the mass of the packaging
     */
    public BigDecimal getMass() {
        return BigDecimal.valueOf(Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6));
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PolyBag polyBag = (PolyBag) o;
        return getVolume().equals(polyBag.getVolume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVolume());
    }
}
