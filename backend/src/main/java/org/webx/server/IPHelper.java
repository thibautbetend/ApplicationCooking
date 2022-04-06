package org.webx.server;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.Request;
import org.webx.client.exceptions.ApiFailedException;

public class IPHelper {
    private static final String IP_INFO = "https://ipinfo.io/ip";

    /**
     * Get the remote address for the given client Request.
     * If the client's IP is on the same network (e.g. 192.x or 127.x)
     * the server will call a distant API to get its own IP which is the same
     * public IP as the client one.
     *
     * @param request The client request
     * @return The client's IP
     * @throws ApiFailedException If any error occurs during the IP API call.
     */
    public String getRemoteAddress(Request request) throws ApiFailedException {
        String remoteAddress = request.getRemoteAddr();
        if (remoteAddress.startsWith("192.") || remoteAddress.startsWith("127.")) {
            remoteAddress = getSelfIP();
        }

        return remoteAddress;
    }

    /**
     * @return The public ip of the current server
     * @throws ApiFailedException If any error occurs during the API call.
     */
    public String getSelfIP() throws ApiFailedException {
        try {
            return ClientBuilder.newClient()
                .target(IP_INFO)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        } catch (ClientErrorException e) {
            throw new ApiFailedException(IP_INFO, Integer.toString(e.getResponse().getStatus()));
        }
    }
}
