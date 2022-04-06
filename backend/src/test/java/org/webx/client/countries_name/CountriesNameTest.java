package org.webx.client.countries_name;

import org.junit.Test;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.exceptions.InvalidCountryCodeException;

import static org.junit.Assert.*;


public class CountriesNameTest {
    private static final String COUNTRY_CODE = "FR";
    private static final String COUNTRY_CODE_INVALID = "ZZ";
    private static final String ADJECTIVE_EXIST = "French";
    private static final String ADJECTIVE_NOT_EXIST = "fneigneing";

    @Test
    public void fetchCountriesNameInfo_Name() throws InvalidCountryCodeException, ApiFailedException {
        assertEquals("France", new CountriesNameClient().fetchGeoApiInfo(COUNTRY_CODE).getName());
    }

    @Test
    public void fetchCountriesNameInfo_Adjective() throws InvalidCountryCodeException, ApiFailedException {
        assertEquals("French", new CountriesNameClient().fetchGeoApiInfo(COUNTRY_CODE).getAdjective());
    }

    @Test(expected = InvalidCountryCodeException.class)
    public void fetchCountriesNameInfo_throw() throws InvalidCountryCodeException, ApiFailedException {
        new CountriesNameClient().fetchGeoApiInfo(COUNTRY_CODE_INVALID);
    }

    @Test
    public void fetchAdjectiveInfo_exist() {
        assertTrue(new CountriesNameClient().fetchTheMealDBApiInfo(ADJECTIVE_EXIST));
    }

    @Test
    public void fetchAdjectiveInfo_notExist() {
        assertFalse(new CountriesNameClient().fetchTheMealDBApiInfo(ADJECTIVE_NOT_EXIST));
    }
}
