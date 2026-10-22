package com.mapcontrol.vehicle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Araç kimlik bilgilerini (Ticari Model Adı, Platform Kodu, Model Yılı, Yazılım Sürümü vb.) taşır.
 */
public final class VehicleIdentity {

    @NonNull public final String marketName;
    @NonNull public final String platformCode;
    @NonNull public final String modelYear;
    @NonNull public final String softwareVersion;
    @Nullable public final String vin;
    @Nullable public final String description;

    public VehicleIdentity(@NonNull String marketName, @NonNull String platformCode,
            @NonNull String modelYear, @NonNull String softwareVersion,
            @Nullable String vin, @Nullable String description) {
        this.marketName = marketName;
        this.platformCode = platformCode;
        this.modelYear = modelYear;
        this.softwareVersion = softwareVersion;
        this.vin = vin;
        this.description = description;
    }

    @Override
    public String toString() {
        return "VehicleIdentity{" +
                "marketName='" + marketName + '\'' +
                ", platformCode='" + platformCode + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
