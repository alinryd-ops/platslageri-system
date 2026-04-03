package com.platslageri.platslageri_app.model;

/**
 * Olika kundtyper
 */
public enum KundTyp {

    PEAB,
    PRIVAT;

    /**
     * Hämtar timpris beroende på kund
     */
    public double getTimpris() {
        return switch (this) {
            case PEAB -> 650.0;
            case PRIVAT -> 500.0;
        };
    }
}
