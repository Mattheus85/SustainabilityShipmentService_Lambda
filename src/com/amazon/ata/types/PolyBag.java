package com.amazon.ata.types;

import java.math.BigDecimal;

public class PolyBag extends Packaging {

    private BigDecimal volume;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
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

}
