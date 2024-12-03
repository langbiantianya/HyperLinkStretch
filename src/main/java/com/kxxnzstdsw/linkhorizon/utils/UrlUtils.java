package com.kxxnzstdsw.linkhorizon.utils;

import org.apache.logging.log4j.util.Strings;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
    @SuppressWarnings("deprecation")
    public static boolean validURL(String urlString) {
        if (Strings.isEmpty(urlString)) {
            return false;
        }

        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
