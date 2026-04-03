package com.platslageri.platslageri_app.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Fördefinierade materialtyper (dropdown i appen)
 */
   public enum MaterialTyp {
    FA,
    PLX,
    KOPPAR,
    MASSING,
    ROSTFRI;

    @JsonCreator
    public static MaterialTyp fromString(String value) {
        return MaterialTyp.valueOf(value.toUpperCase()); 
    }

    /**
     * Visningsnamn (snyggare i UI senare)
     */
    public String getDisplayName() {
        return switch (this) {
            case KOPPAR -> "Kopparplåt";
            case ROSTFRI -> "Rostfri plåt";
            case MASSING -> "Mässingplåt";
            case PLX -> "PLX plåt";
            case FA -> "FA plåt";
        };
    }
}
