package com.platslageri.platslageri_app.model;

/**
 * Olika kundtyper
 */
public enum KundTyp {

    PRIVAT,
    FÖRETAG;

    public double getTimpris() {
        return switch (this) {
            case PRIVAT -> 500.0;
            case FÖRETAG -> 650.0;
        };
    }
}
