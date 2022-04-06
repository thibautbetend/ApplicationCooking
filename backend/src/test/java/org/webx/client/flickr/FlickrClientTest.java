package org.webx.client.flickr;

import org.junit.Before;
import org.junit.Test;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.flickr.Photo.PhotosSearchResponse;
import org.webx.client.flickr.User.User;
import org.webx.client.geo.GeoClient;
import org.webx.client.geo.GeoInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FlickrClientTest {

    private static final String IRIT_IP = "141.115.28.2";
    private static GeoInfo IRIT_INFO ;

    @Before
    public void setup() throws ApiFailedException {
        IRIT_INFO = new GeoClient().fetchGeoInfo(IRIT_IP);
    }

    @Test
    public void fetchURLs() throws ApiFailedException {
        PhotosSearchResponse photos = new FlickrClient().fetchURLs(IRIT_INFO.getGeoCoord());

        assertNotNull(photos);
        assertEquals(5, photos.getPhotos().getPhoto().size());
    }

    @Test
    public void fetchUsername() throws ApiFailedException {
        User user = new FlickrClient().fetchUser("192676435@N06");
        assertEquals("axel.laffite", user.getPerson().getUsername().getContent());
        assertEquals("Axel Laffite", user.getPerson().getRealname().getContent());
    }

}