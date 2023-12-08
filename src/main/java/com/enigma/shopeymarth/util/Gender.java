package com.enigma.shopeymarth.util;

public enum Gender {
    MALE("Laki-laki"),
    FEMALE("Perempuan");
    String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
