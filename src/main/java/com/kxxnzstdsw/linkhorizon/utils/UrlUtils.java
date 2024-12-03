package com.kxxnzstdsw.linkhorizon.utils;

import org.apache.logging.log4j.util.Strings;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtils {
    public static boolean validURL(String urlString) {
        if (Strings.isEmpty(urlString)) {
            return false;
        }

        try {
            new URI(urlString).toURL();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}
