package org.webx;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;


public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8081/api";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.webx package
        final ResourceConfig rc = new ResourceConfig().packages("org.webx");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        HttpHandler httpHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(), "/");
        server.getServerConfiguration().addHttpHandler(httpHandler, "/");

        return server;
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Jersey app started with WADL available at \nHit enter to stop it...");
        System.in.read();
        server.shutdownNow();
    }
}

