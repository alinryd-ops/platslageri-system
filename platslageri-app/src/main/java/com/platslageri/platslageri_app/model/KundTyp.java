package com.platslageri.platslageri_app.model;

/**
 * Olika kundtyper
 */
public enum KundTyp {

    PRIVAT,
    FORETAG;

    public double getTimpris() {
        return switch (this) {
            case PRIVAT -> 500.0;
            case FORETAG -> 650.0;
        };
    }
}
