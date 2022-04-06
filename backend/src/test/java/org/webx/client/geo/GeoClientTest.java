package org.webx.client.geo;

import org.junit.Test;
import org.webx.client.exceptions.ApiFailedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GeoClientTest {

    private static final String GOOGLE_IP = "216.58.205.195";

    @Test
    public void fetchGeoInfo() throws ApiFailedException {
        assertEquals("United States of America", new GeoClient().fetchGeoInfo(GOOGLE_IP).getName());
    }

    /**
     * Check the information of the Google IP
     */
    @Test
    public void geoInfoGoogleCheck() throws ApiFailedException {
        GeoInfo info = new GeoClient().fetchGeoInfo(GOOGLE_IP);

        assertEquals("United States of America", info.getName());
        assertEquals("US", info.getCode());
        assertNotNull(info.getGeoCoord().getMax_latitude());
        assertNotNull(info.getGeoCoord().getMax_longitude());
        assertNotNull(info.getGeoCoord().getMin_latitude());
        assertNotNull(info.getGeoCoord().getMin_longitude());
    }

    @Test
    public void fetchGeoInfoForCountry() throws ApiFailedException {
        GeoInfo info = new GeoClient().fetchGeoInfoForCountry("fr");

        assertEquals("France", info.getName());
        assertEquals("fr", info.getCode().toLowerCase());
        assertNotNull(info.getGeoCoord().getMax_latitude());
        assertNotNull(info.getGeoCoord().getMax_longitude());
        assertNotNull(info.getGeoCoord().getMin_latitude());
        assertNotNull(info.getGeoCoord().getMin_longitude());
    }
}