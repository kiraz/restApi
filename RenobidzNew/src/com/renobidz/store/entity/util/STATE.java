package com.renobidz.store.entity.util;

import java.util.HashMap;
import java.util.Map;

public enum STATE {
	ALABAMA("Alabama", "AL", COUNTRY.US),
    ALASKA("Alaska", "AK", COUNTRY.US),
    AMERICAN_SAMOA("American Samoa", "AS", COUNTRY.US),
    ARIZONA("Arizona", "AZ", COUNTRY.US),
    ARKANSAS("Arkansas", "AR", COUNTRY.US),
    CALIFORNIA("California", "CA", COUNTRY.US),
    COLORADO("Colorado", "CO", COUNTRY.US),
    CONNECTICUT("Connecticut", "CT", COUNTRY.US),
    DELAWARE("Delaware", "DE", COUNTRY.US),
    DISTRICT_OF_COLUMBIA("District of Columbia","DC", COUNTRY.US),
    FEDERATED_STATES_OF_MICRONESIA("Federated States of Micronesia", "FM", COUNTRY.US),
    FLORIDA("Florida", "FL", COUNTRY.US),
    GEORGIA("Georgia", "GA", COUNTRY.US),
    GUAM("Guam", "GU", COUNTRY.US),
    HAWAII("Hawaii", "HI", COUNTRY.US),
    IDAHO("Idaho", "ID", COUNTRY.US),
    ILLINOIS("Illinois", "IL", COUNTRY.US),
    INDIANA("Indiana", "IN", COUNTRY.US),
    IOWA("Iowa", "IA", COUNTRY.US),
    KANSAS("Kansas", "KS", COUNTRY.US),
    KENTUCKY("Kentucky", "KY", COUNTRY.US),
    LOUISIANA("Louisiana", "LA", COUNTRY.US),
    MAINE("Maine", "ME", COUNTRY.US),
    MARYLAND("Maryland", "MD", COUNTRY.US),
    MARSHALL_ISLANDS("Marshall Islands", "MH", COUNTRY.US),
    MASSACHUSETTS("Massachusetts", "MA", COUNTRY.US),
    MICHIGAN("Michigan", "MI", COUNTRY.US),
    MINNESOTA("Minnesota", "MN", COUNTRY.US),
    MISSISSIPPI("Mississippi", "MS", COUNTRY.US),
    MISSOURI("Missouri", "MO", COUNTRY.US),
    MONTANA("Montana", "MT", COUNTRY.US),
    NEBRASKA("Nebraska", "NE", COUNTRY.US),
    NEVADA("Nevada", "NV", COUNTRY.US),
    NEW_HAMPSHIRE("New Hampshire", "NH", COUNTRY.US),
    NEW_JERSEY("New Jersey", "NJ", COUNTRY.US),
    NEW_MEXICO("New Mexico", "NM", COUNTRY.US),
    NEW_YORK("New York", "NY", COUNTRY.US),
    NORTH_CAROLINA("North Carolina", "NC", COUNTRY.US),
    NORTH_DAKOTA("North Dakota", "ND", COUNTRY.US),
    NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands", "MP", COUNTRY.US),
    OHIO("Ohio", "OH", COUNTRY.US),
    OKLAHOMA("Oklahoma", "OK", COUNTRY.US),
    OREGON("Oregon", "OR", COUNTRY.US),
    PALAU("Palau", "PW", COUNTRY.US),
    PENNSYLVANIA("Pennsylvania", "PA", COUNTRY.US),
    PUERTO_RICO("Puerto Rico", "PR", COUNTRY.US),
    RHODE_ISLAND("Rhode Island", "RI", COUNTRY.US),
    SOUTH_CAROLINA("South Carolina", "SC", COUNTRY.US),
    SOUTH_DAKOTA("South Dakota", "SD", COUNTRY.US),
    TENNESSEE("Tennessee", "TN", COUNTRY.US),
    TEXAS("Texas", "TX", COUNTRY.US),
    UTAH("Utah", "UT", COUNTRY.US),
    VERMONT("Vermont", "VT", COUNTRY.US),
    VIRGIN_ISLANDS("Virgin Islands", "VI", COUNTRY.US),
    VIRGINIA("Virginia", "VA", COUNTRY.US),
    WASHINGTON("Washington", "WA", COUNTRY.US),
    WEST_VIRGINIA("West Virginia", "WV", COUNTRY.US),
    WISCONSIN("Wisconsin", "WI", COUNTRY.US),
    WYOMING("Wyoming", "WY", COUNTRY.US),

    AB("Alberta", "AB", COUNTRY.CA),
    BC("British Columbia", "BC", COUNTRY.CA),
    MB("Manitoba", "MB", COUNTRY.CA),
    NB("New Brunswick", "NB", COUNTRY.CA),
    NL("Newfoundland and Labrador", "NL", COUNTRY.CA),
    NS("Nova Scotia", "NS", COUNTRY.CA),
    ON("Ontario", "ON", COUNTRY.CA),
    PE("Prince Edward Island", "PE", COUNTRY.CA),
    QC("Quebec", "QC", COUNTRY.CA),
    SK("Saskatchewan", "SK", COUNTRY.CA),
    NT("Northwest Territories", "NT", COUNTRY.CA),
    NU("Nunavut", "NU", COUNTRY.CA),
    YT("Yukon", "YT", COUNTRY.CA),

    //TODO Character encoding??
    DISTRITO_FEDERAL("Distrito Federal", "DIF", COUNTRY.MX),
    AGUASCALIENTES("Aguascalientes", "AGU", COUNTRY.MX),
    BAJA_CALIFORNIA("Baja California", "BCN", COUNTRY.MX),
    BAJA_CALIFORNIA_SUR("Baja California Sur", "BCS", COUNTRY.MX),
    CAMPECHE("Campeche", "CAM", COUNTRY.MX),
    COAHUILA("Coahuila", "COA", COUNTRY.MX),
    COLIMA("Colima", "COL", COUNTRY.MX),
    CHIAPAS("Chiapas", "CHP", COUNTRY.MX),
    CHIHUAHUA("Chihuahua", "CHH", COUNTRY.MX),
    DURANGO("Durango", "DUR", COUNTRY.MX),
    GUANAJUATO("Guanajuato", "GUA", COUNTRY.MX),
    GUERRERO("Guerrero", "GRO", COUNTRY.MX),
    HIDALGO("Hidalgo", "HID", COUNTRY.MX),
    JALISCO("Jalisco", "JAL", COUNTRY.MX),
    //MEXICO("México", "MEX", COUNTRY.MEXICO),
    //MICHOACAN("Michoacán", "MIC", COUNTRY.MEXICO),
    MORELOS("Morelos", "MOR", COUNTRY.MX),
    NAYARIT("Nayarit", "NAY", COUNTRY.MX),
    //NUEVO_LEON("Nuevo León", "NLE", COUNTRY.MEXICO),
    OAXACA("Oaxaca", "OAX", COUNTRY.MX),
    PUEBLA("Puebla", "PUE", COUNTRY.MX),
    //QUERETARO("Querétaro", "QUE", COUNTRY.MEXICO),
    QUITANA_ROO("Quintana Roo", "ROO", COUNTRY.MX),
    //SAN_LUIS_POTOSI("San Luis Potosí", "SLP", COUNTRY.MEXICO),
    SINALOA("Sinaloa", "SIN", COUNTRY.MX),
    SORONA("Sonora", "SON", COUNTRY.MX),
    TABASCO("Tabasco", "TAB", COUNTRY.MX),
    TAMAULIAS("Tamaulipas", "TAM", COUNTRY.MX),
    TLAXCALA("Tlaxcala", "TLA", COUNTRY.MX),
    VERACRUZ("Veracruz", "VER", COUNTRY.MX),
    //YUCATAN("Yucatán", "YUC", COUNTRY.MEXICO),
    ZACATECAS("Zacatecas", "ZAC", COUNTRY.MX),

    UNKNOWN("Unknown", "", COUNTRY.UNKNOWN);

    //The state's name.
	private String name;

    //The state's abbreviation.
	private String abbreviation;

    //The state's country
    private COUNTRY country;


	/**
	 * The set of states addressed by abbreviations.
	 */
	private static final Map<String, STATE> STATES_BY_ABBR = new HashMap<String, STATE>();

	/* static initializer */
	static {
		for (STATE state : values()) {
			STATES_BY_ABBR.put(state.getAbbreviation(), state);
		}
	}

	/**
	 * Constructs a new state.
	 * 
	 * @param name
	 *            the state's name.
	 * @param abbreviation
	 *            the state's abbreviation.
     * @param country
     *            the state's country.
	 */
	STATE(String name, String abbreviation, COUNTRY country) {
		this.name = name;
		this.abbreviation = abbreviation;
        this.country = country;
	}

	/**
	 * Returns the state's abbreviation.
	 * 
	 * @return the state's abbreviation.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Gets the enum constant with the specified abbreviation.
	 * 
	 * @param abbr
	 *            the state's abbreviation.
	 * @return the enum constant with the specified abbreviation.
	 * @throws
	 *             if the abbreviation is invalid.
	 */
	public static STATE valueOfAbbreviation(final String abbr) {
		final STATE state = STATES_BY_ABBR.get(abbr);
		if (state != null) {
			return state;
		} else {
			return UNKNOWN;
		}
	}

	public static STATE valueOfName(final String name) {
		final String enumName = name.toUpperCase().replaceAll(" ", "_");
		try {
			return valueOf(enumName);
		} catch (final IllegalArgumentException e) {
			return STATE.UNKNOWN;
		}
	}

    public static STATE valueOfCountry(final COUNTRY country) {
        final STATE state = STATES_BY_ABBR.get(country);
        if (state != null) {
            return state;
        } else {
            return UNKNOWN;
        }
    }

	@Override
	public String toString() {
		return name;
	}
}
