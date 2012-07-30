package com.teamboid.twitterapi.client;

/**
 * @author Aidan Follestad
 */
public class HttpParam {

    public HttpParam(String name, String value) {
        _name = name;
        _value = value;
    }

    private String _name;
    private String _value;

    public String getName() { return _name; }
    public String getValue() { return _value; }
}
