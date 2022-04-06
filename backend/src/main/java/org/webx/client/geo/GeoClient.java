package org.webx.client.geo;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import org.webx.client.exceptions.ApiFailedException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * GeoClient is used to provide functions that
 * enable to fetch information from distant
 * APIs.
 */
public class GeoClient {
    public static final String GEO_API = "https://api.ipgeolocationapi.com/";
    public static final String GEO_LOCATE = "geolocate";
    public static final String COUNTRIES = "countries";


    /**
     * Fetch the geographic information for the given ip.
     *
     * @param ip The ip for which the geographic information should be searched.
     * @return The geographic information as a {@link org.webx.client.geo.GeoInfo}
     */
    public GeoInfo fetchGeoInfo(String ip) throws ApiFailedException {
        try {
            return getGeoInfoFromRequest(
                ClientBuilder.newClient()
                    .target(GEO_API)
                    .path(GEO_LOCATE)
                    .path(ip)
                    .request(MediaType.APPLICATION_JSON_TYPE)
            );

        } catch (ClientErrorException e) {
            throw new ApiFailedException(GEO_API, Integer.toString(e.getResponse().getStatus()));
        } catch (ValidationException e) {
            throw new ApiFailedException(GEO_API, "Unable to validate response from server");
        }
    }

    /**
     * Fetch the geographic information for the given country code.
     *
     * @param code The country code for which the geographic information should be searched.
     * @return The geographic information as a {@link org.webx.client.geo.GeoInfo}
     */
    public GeoInfo fetchGeoInfoForCountry(String code) throws ApiFailedException {
        try {
            return getGeoInfoFromRequest(
                ClientBuilder.newClient()
                    .target(GEO_API)
                    .path(COUNTRIES)
                    .path(code)
                    .request(MediaType.APPLICATION_JSON_TYPE)
            );
        } catch (ClientErrorException e) {
            throw new ApiFailedException(GEO_API, Integer.toString(e.getResponse().getStatus()));
        } catch (ValidationException e) {
            throw new ApiFailedException(GEO_API, "Possibly invalid country code");
        }
    }

    /**
     * Returns the requested data validated with Hibernate's validators.
     *
     * @param request The request to perform.
     * @return The validated data.
     * @throws ValidationException If the provided data contains error during validation.
     */
    private GeoInfo getGeoInfoFromRequest(Invocation.Builder request) throws ValidationException {
        try {
            GeoInfo result = request.get(GeoInfo.class);

            Set<ConstraintViolation<GeoInfo>> errors = Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(result);

            if (!errors.isEmpty()) {
                throw new ValidationException();
            }

            return result;
        } catch (Exception e) {
            throw new ValidationException();
        }
    }

}
