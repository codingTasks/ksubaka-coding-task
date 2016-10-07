package com.ksubaka.codingtask.webapi.connector;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;

public class JerseyApiConnector implements WebApiConnector {
    private static final int HTTP_SUCCESS = 200;
    private static Logger logger = Logger.getLogger(JerseyApiConnector.class);
    private final Client client = Client.create();

    @Override
    public String getResponse(String uri) {
        WebResource webResource = client.resource(uri);
        logger.debug(String.format("Sending request for uri: %s", uri));
        ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);

        if (clientResponse.getStatus() != HTTP_SUCCESS) {
            throw new RuntimeException(String.format("Failed - HTTP error code : %s, info: %s", clientResponse
                    .getStatus(), clientResponse.getStatusInfo()));
        }

        return clientResponse.getEntity(String.class);
    }
}
