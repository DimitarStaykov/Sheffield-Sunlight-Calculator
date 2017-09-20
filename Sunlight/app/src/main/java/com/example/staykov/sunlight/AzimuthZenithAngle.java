package com.example.staykov.sunlight;


/**
 *This is a Java library for finding topocentric solar coordinates,
 * i.e. the sun’s position on the sky at a given date, latitude,
 * and longitude (and other parameters).
 * Calculations are based on well-known published algorithms:
 * SPA by Reda and Andreas and, alternatively,
 * Grena/ENEA by Grena or PSA by Blanco-Muriel et al.
 *
 */

/**
 * A simple wrapper class for keeping an azimuth/zenith angle pair of values.
 */
public class AzimuthZenithAngle {
    private final double azimuth;
    private final double zenithAngle;

    public AzimuthZenithAngle(final double azimuth, final double zenithAngle) {
        this.zenithAngle = zenithAngle;
        this.azimuth = azimuth;
    }

    public final double getZenithAngle() {
        return zenithAngle;
    }

    public final double getAzimuth() {
        return azimuth;
    }

    @Override
    public String toString() {
        return String.format("azimuth %.6f°, zenith angle %.6f°", azimuth, zenithAngle);
    }

}
