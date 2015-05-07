package com.renobidz.store.entity.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lmgagne on 15-02-02.
 */
public enum PROVINCE {
    ONTARIO("Ontario", "ON"),
    QUEBEC("Quebec", "QC"),
    NOVA_SCOTIA("Nova Scotia", "NS"),
    NEW_BRUNSWICK("New Brunswick", "NB"),
    MANITOBA("Manitoba", "MB"),
    BRITISH_COLUMBIA("British Columbia", "BC"),
    PRINCE_EDWARD_ISLAND("Prince Edward Island", "PE"),
    SASKATCHEWAN("Saskatchewan", "SK"),
    ALBERTA("Alberta", "AB"),
    NEWFOUNDLAND_AND_LABRADOR("Newfoundland & Labrador", "NL"),
    NORTHWEST_TERRITORIES("Northwest Territories", "NT"),
    YUKON("Yukon", "YT"),
    NUNAVUT("Nunavut", "NU"),
    UNKNOWN("Unknown", "");

    /**
     * The province's name.
     */
    private String name;

    /**
     * The province's abbreviation.
     */
    private String abbreviation;

    /**
     * The set of states addressed by abbreviations.
     */
    private static final Map<String, PROVINCE> PROVINCES_BY_ABBR = new HashMap<String, PROVINCE>();

    /* static initializer */
    static {
        for (PROVINCE province : values()) {
            PROVINCES_BY_ABBR.put(province.getAbbreviation(), province);
        }
    }

    /**
     * Constructs a new state.
     *
     * @param name
     *            the province's name.
     * @param abbreviation
     *            the province's abbreviation.
     */
    PROVINCE(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the province's abbreviation.
     *
     * @return the province's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr
     *            the province's abbreviation.
     * @return the enum constant with the specified abbreviation.
     * @throws SunlightException
     *             if the abbreviation is invalid.
     */
    public static PROVINCE valueOfAbbreviation(final String abbr) {
        final PROVINCE province = PROVINCES_BY_ABBR.get(abbr);
        if (province != null) {
            return province;
        } else {
            return UNKNOWN;
        }
    }

    public static PROVINCE valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return PROVINCE.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}


