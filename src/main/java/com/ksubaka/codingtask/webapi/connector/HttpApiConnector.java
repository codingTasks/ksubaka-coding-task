package com.ksubaka.codingtask.webapi.connector;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpApiConnector implements WebApiConnector {
    private static Logger logger = Logger.getLogger(WebApiConnector.class);
    private final HttpClient client = new DefaultHttpClient();

    @Override
    public String getResponse(String uri) {
        String response;
        logger.debug(String.format("Sending request for uri: %s", uri));

        try {
            HttpGet request = new HttpGet(uri);
            response = EntityUtils.toString(client.execute(request).getEntity());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to process request for uri: %s", uri));
        }

        return response;
    }
}
