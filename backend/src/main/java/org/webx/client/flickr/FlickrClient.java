package org.webx.client.flickr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.flickr.Photo.Photo;
import org.webx.client.flickr.Photo.PhotosSearchResponse;
import org.webx.client.flickr.User.User;
import org.webx.client.geo.BoundingBox;

public class FlickrClient {
    private final static String API_KEY = "e6e5e650325f69d7d5b7c99216bf0402";
    private final static String FLICKR_API = "https://api.flickr.com/services/rest/";
    private final static String API_NAME = "https://api.flickr.com";


    /**
     * Fetch the flickr API to obtain pictures for a given
     * country bounding box.
     *
     * @param bbox The bounding for which the picture will be searched for
     * @return A PhotosSearchResponse containing the desired pictures (0 to 5)
     */

    public PhotosSearchResponse fetchURLs(BoundingBox bbox) throws ApiFailedException {
        try {
            PhotosSearchResponse response = generateJSONFlickrCall()
                .queryParam("method", "flickr.photos.search")
                .queryParam("bbox", String.join (
                    ",",
                    Double.toString(bbox.getMin_longitude()),
                    Double.toString(bbox.getMin_latitude()),
                    Double.toString(bbox.getMax_longitude()),
                    Double.toString(bbox.getMax_latitude())
                ))
                .queryParam("extras", "url_c")
                .queryParam("per_page", 5)
                .queryParam("page", 1)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PhotosSearchResponse.class);

            for (Photo photo : response.getPhotos().getPhoto()) {
                final String owner = photo.getOwner();
                photo.setOwner(
                    fetchUser(owner).getPerson().getUsername().getContent()
                );
            }

            return response;
        } catch (ClientErrorException e) {
            throw new ApiFailedException(API_NAME, Integer.toString(e.getResponse().getStatus()));
        }
    }

    /**
     * Fetch the username for a given user id.
     *
     * @param userID The user id which will be used to
     *               find the username
     * @return The username or null if the user is not found.
     */
    public User fetchUser(String userID) throws ApiFailedException {
        try {
            return generateJSONFlickrCall()
                .queryParam("method", "flickr.people.getInfo")
                .queryParam("user_id", userID)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(User.class);
        } catch (ClientErrorException e) {
            throw new ApiFailedException(API_NAME, Integer.toString(e.getResponse().getStatus()));
        }
    }

    /**
     * Generate a client that will call the
     * JSON API with the base link API.
     * The method should be added to that client
     * and so, the query parameters too.
     * <p>
     * The client is already configured to get a JSON
     * formatted response and the API key is also
     * already configured.
     *
     * @return A client ready to perform a JSON formatted
     * request on the Flickr API
     */
    private WebTarget generateJSONFlickrCall() {
        return ClientBuilder.newClient()
            .target(FLICKR_API)
            .queryParam("api_key", API_KEY)
            .queryParam("format", "json")
            .queryParam("nojsoncallback", 1);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
