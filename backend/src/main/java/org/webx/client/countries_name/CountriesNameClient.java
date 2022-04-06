package org.webx.client.countries_name;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.core.MediaType;
import org.webx.client.countries_name.countries.AdjectiveInfo;
import org.webx.client.countries_name.countries.CountriesNameInfo;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.exceptions.InvalidCountryCodeException;

/**
 * CountriesNameClient is used to provide functions that
 * enable to fetch information from distant
 * APIs.
 */
public class CountriesNameClient {

    public static final String GEO_API = "https://api.ipgeolocationapi.com/countries/";
    public static final String THEMEALDB_API = "https://www.themealdb.com/api/json/v1/1/filter.php";

    /**
     * Fetch countries information for the given code.
     *
     * @param code The code for a country (2 letters code from ISO 3166-1 alpha-2)
     * @return The name and the nationality of the country
     */
    public CountriesNameInfo fetchGeoApiInfo(String code) throws ApiFailedException, InvalidCountryCodeException {
        try {
            CountriesNameInfo infos = ClientBuilder.newClient()
                .target(GEO_API)
                .path(code)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(CountriesNameInfo.class);

            if (!fetchTheMealDBApiInfo(infos.getAdjective())) {
                throw new InvalidCountryCodeException();
            }

            return infos;
        } catch (ClientErrorException e) {
            throw new ApiFailedException(GEO_API, Integer.toString(e.getResponse().getStatus()));
        }
    }


    /**
     * Verify if the country is referenced in the MealDB .
     *
     * @param adjective The adjective for a country
     * @return a boolean indicating if the country is referenced in TheMealDB
     */
    public boolean fetchTheMealDBApiInfo(String adjective) {
        try {
            return ClientBuilder.newClient()
                    .target(THEMEALDB_API)
                    .queryParam("a", adjective)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(AdjectiveInfo.class)
                    .getMeal() != null;
        } catch (ResponseProcessingException e) {
            return false;
        }
    }
}
