package com.github.lpedrosa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogFinder implements LogFinder {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleLogFinder.class);

    private final String host;
    private final String port;

    public SimpleLogFinder(String host, String port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Collection<FinderInstance> instances() throws LogFinderException {
        // create URL
        final URL url;
        try {
            url = new URL("http://"+host+":"+port);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }

        // open connection
        final HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (IOException ex) {
            throw new LogFinderException("Problem establishing LogFinder connection", ex);
        }

        // handle response
        int code = -1;
        final String body;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            conn.getResponseCode();
            body = reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException ex) {
            throw new LogFinderException("Problem reading response from LogFinder", ex);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("LogFinder server response [code: {} body: {}]", code, body);
        }

        return Collections.emptyList();
    }
}
