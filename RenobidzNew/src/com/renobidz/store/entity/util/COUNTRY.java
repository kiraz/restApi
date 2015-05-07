package com.renobidz.store.entity.util;

import java.util.HashMap;
import java.util.Map;

public enum COUNTRY {
	US("United States", "USA"), CA("Canada", "CA"), MX("Mexico", "MX"), UNKNOWN("Unknown", "");

    private String name;
    private String abbreviation;

    private static final Map<String, COUNTRY> COUNTRIES_BY_ABBR = new HashMap<String, COUNTRY>();

    /* static initializer */
    static {
        for (COUNTRY country : values()) {
            COUNTRIES_BY_ABBR.put(country.getAbbreviation(), country);
        }
    }

    /**
     * Constructs a new country.
     *
     * @param name
     *            the country's name.
     * @param abbreviation
     *            the country's abbreviation(ISO).
     */
    COUNTRY(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the country's abbreviation.
     *
     * @return the country's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr
     *            the country's abbreviation.
     * @return the enum constant with the specified abbreviation.
     * @throws SunlightException
     *             if the abbreviation is invalid.
     */
    public static COUNTRY valueOfAbbreviation(final String abbr) {
        final COUNTRY country = COUNTRIES_BY_ABBR.get(abbr);
        if (country != null) {
            return country;
        } else {
            return UNKNOWN;
        }
    }

    public static COUNTRY valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return COUNTRY.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
