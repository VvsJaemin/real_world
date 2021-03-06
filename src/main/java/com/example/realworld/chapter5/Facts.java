package com.example.realworld.chapter5;

import java.util.HashMap;
import java.util.Map;

public class Facts {

    private final Map<String, String> facts = new HashMap<>();

    public String getFacts(final String name) {
        return this.facts.get(name);
    }

    public void setFacts(final String name, final String value) {
        this.facts.put(name, value);
    }
}
