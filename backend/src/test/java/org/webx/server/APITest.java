package org.webx.server;

import jakarta.json.JsonReader;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;
import org.leadpony.justify.api.Problem;
import org.leadpony.justify.api.ProblemHandler;
import org.webx.Main;
import org.webx.client.exceptions.ApiFailedException;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APITest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        APIResponse responseMsg = target
            .path("findCountry")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(APIResponse.class);

        assertEquals("FR", responseMsg.getCountryAlpha2());
    }

    @Test
    public void testGetItCountryCode() {
        APIResponse responseMsg = target
            .path("findCountry")
            .queryParam("country","fr")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(APIResponse.class);

        assertEquals("FR", responseMsg.getCountryAlpha2());
    }

    @Test
    public void testGetITJSONSchema() {
        assertTrue(new File("src/test/resources/eat-and-see.schema.json").exists());
        String responseMsg = target
            .path("findCountry")
            .queryParam("country","fr")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(String.class);

        InputStream stream = new ByteArrayInputStream(responseMsg.getBytes());

        JsonValidationService service = JsonValidationService.newInstance();
        JsonSchema schema = service.readSchema(Paths.get("src/test/resources/eat-and-see.schema.json"));
        ProblemHandler handler = new AvoidProblemHandler();

        try (JsonReader reader = service.createReader(stream, schema, handler)) {
            reader.readValue();
        }
    }

    @Test
    public void testGetITJSONSchemaInvalidRequest() {
        assertTrue(new File("src/test/resources/eat-and-see.schema.json").exists());
        String responseMsg = target
            .path("findCountry")
            .queryParam("country","qsdqsdsq")
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(String.class);

        InputStream stream = new ByteArrayInputStream(responseMsg.getBytes());

        JsonValidationService service = JsonValidationService.newInstance();
        JsonSchema schema = service.readSchema(Paths.get("src/test/resources/eat-and-see.schema.json"));
        ProblemHandler handler = new AvoidProblemHandler();

        try (JsonReader reader = service.createReader(stream, schema, handler)) {
            reader.readValue();
        }
    }

    @Test
    public void testGetSelfIp() throws ApiFailedException {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        String ip = new IPHelper().getSelfIP();
        assertTrue(validator.isValid(ip));
    }


    /**
     * Custom class to throw an error in case of schema problems
     */
    private static class AvoidProblemHandler implements ProblemHandler {
        @Override
        public void handleProblems(List<Problem> problems) {
            throw new IllegalStateException(
                problems.stream()
                    .map(Problem::toString)
                    .collect(Collectors.joining("\n"))
            );
        }
    }
}
