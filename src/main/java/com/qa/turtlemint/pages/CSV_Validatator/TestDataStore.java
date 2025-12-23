package com.qa.turtlemint.pages.CSV_Validatator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestDataStore {

    private static final Map<String, String> STORE = new ConcurrentHashMap<>();

    private TestDataStore() {

    }

    public static void put(String key, String value) {
        STORE.put(key, value);
    }

    public static String get(String key) {
        return STORE.get(key);
    }

}
