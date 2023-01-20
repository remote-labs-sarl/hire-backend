package com.remotelabs.hire.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCreator {

    public static String convertToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
