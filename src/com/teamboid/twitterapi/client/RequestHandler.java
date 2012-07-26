package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Handles web requests for {@link TwitterBase}
 *
 * @author Aidan Follestad
 */
class RequestHandler {

    public OAuthService _oauth;
    public Token _oauthToken;
    public Authorizer.DebugLevel _debugOn;
    public boolean _ssl;


    private String getApiUrl() {
        if (_ssl) return "https://api.twitter.com/1";
        else return "http://api.twitter.com/1";
    }

    public Response get(String url, boolean apiUrl) throws Exception {
        if (apiUrl) url = getApiUrl() + url;
        if (_debugOn != Authorizer.DebugLevel.OFF) {
            System.out.println("[GET]: " + url);
        }
        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        if (_oauth != null) {
            _oauth.signRequest(_oauthToken, request);
        }
        Response response = request.send();
        String body = response.getBody();
        if (body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body), response.getCode());
        } else if(response.getCode() != 200) {
        	throw new Exception("HTTP GET to " + url + " failed, error code " + response.getCode());
        }
        if (_debugOn == Authorizer.DebugLevel.DEEP) {
            System.out.println(body + "\n");
        }
        return response;
    }

    public Response post(String url, boolean apiUrl, List<HttpParam> params) throws Exception {
        if (apiUrl) url = getApiUrl() + url;
        if (_debugOn != Authorizer.DebugLevel.OFF) {
            System.out.println("[POST]: " + url);
        }
        OAuthRequest request = new OAuthRequest(Verb.POST, url);
        if (params != null) {
            for (HttpParam p : params) request.addBodyParameter(p.getName(), p.getValue());
        }
        if (_oauth != null) {
            _oauth.signRequest(_oauthToken, request);
        }
        Response response = request.send();
        String body = response.getBody();
        if (body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body), response.getCode());
        } else if(response.getCode() != 200) {
        	throw new Exception("HTTP GET to " + url + " failed, error code " + response.getCode());
        }
        if (_debugOn == Authorizer.DebugLevel.DEEP) {
            System.out.println(body + "\n");
        }
        return response;
    }

    public Response post(String url, List<HttpParam> params, File file) throws Exception {
        if (_debugOn != Authorizer.DebugLevel.OFF) {
            System.out.println("[POST]: " + url);
        }
        OAuthRequest request = new OAuthRequest(Verb.POST, url);

        MultipartEntity entity = new MultipartEntity();
        if (params != null) {
            for (HttpParam p : params) {
                entity.addPart(p.getName(), new StringBody(p.getValue()));
            }
        }
        entity.addPart("media", new FileBody(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        entity.writeTo(out);
        request.addPayload(out.toByteArray());
        request.addHeader(entity.getContentType().getName(), entity.getContentType().getValue());

        _oauth.signRequest(_oauthToken, request);
        Response response = request.send();
        String body = response.getBody();
        if (body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body), response.getCode());
        } else if(response.getCode() != 200) {
        	throw new Exception("HTTP POST to " + url + " failed, error code " + response.getCode());
        }
        if (_debugOn == Authorizer.DebugLevel.DEEP) {
            System.out.println(body + "\n");
        }
        return response;
    }
    
    public Response post(String url, List<HttpParam> params, InputStream stream, String fileName) throws Exception {
        if (_debugOn != Authorizer.DebugLevel.OFF) {
            System.out.println("[POST]: " + url);
        }
        OAuthRequest request = new OAuthRequest(Verb.POST, url);

        MultipartEntity entity = new MultipartEntity();
        if (params != null) {
            for (HttpParam p : params) {
                entity.addPart(p.getName(), new StringBody(p.getValue()));
            }
        }
        entity.addPart("media", new InputStreamBody(stream, fileName));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        entity.writeTo(out);
        request.addPayload(out.toByteArray());
        request.addHeader(entity.getContentType().getName(), entity.getContentType().getValue());

        _oauth.signRequest(_oauthToken, request);
        Response response = request.send();
        String body = response.getBody();
        if (body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body), response.getCode());
        } else if(response.getCode() != 200) {
        	throw new Exception("HTTP POST to " + url + " failed, error code " + response.getCode());
        }
        if (_debugOn == Authorizer.DebugLevel.DEEP) {
            System.out.println(body + "\n");
        }
        return response;
    }

    public Response delete(String url, boolean apiUrl) throws Exception {
        if (apiUrl) url = getApiUrl() + url;
        if (_debugOn != Authorizer.DebugLevel.OFF) {
            System.out.println("[DELETE]: " + url);
        }
        OAuthRequest request = new OAuthRequest(Verb.DELETE, url);
        if (_oauth != null) {
            _oauth.signRequest(_oauthToken, request);
        }
        Response response = request.send();
        String body = response.getBody();
        if (body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body), response.getCode());
        } else if(response.getCode() != 200) {
        	throw new Exception("HTTP DELETE to " + url + " failed, error code " + response.getCode());
        }
        if (_debugOn == Authorizer.DebugLevel.DEEP) {
            System.out.println(body + "\n");
        }
        return response;
    }


    public JSONObject getObject(String url) throws Exception {
        return getObject(url, true);
    }
    public JSONObject getObject(String url, boolean apiUrl) throws Exception {
        return new JSONObject(get(url, apiUrl).getBody());
    }

    public JSONObject postObject(String url, List<HttpParam> params) throws Exception {
        return postObject(url, true, params);
    }
    public JSONObject postObject(String url, boolean apiUrl, List<HttpParam> params) throws Exception {
        return new JSONObject(post(url, apiUrl, params).getBody());
    }

    public JSONObject postObject(String url, List<HttpParam> params, File file) throws Exception {
        return postObject(url, true, params, file);
    }
    public JSONObject postObject(String url, boolean apiUrl, List<HttpParam> pairs, File file) throws Exception {
        return new JSONObject(post(url, pairs, file).getBody());
    }
    
    public JSONObject postObject(String url, List<HttpParam> params, InputStream stream, String fileName) throws Exception {
        return postObject(url, true, params, stream, fileName);
    }
    public JSONObject postObject(String url, boolean apiUrl, List<HttpParam> pairs, InputStream stream, String fileName) throws Exception {
        return new JSONObject(post(url, pairs, stream, fileName).getBody());
    }

    public JSONArray getArray(String url) throws Exception {
        return getArray(url, true);
    }
    public JSONArray getArray(String url, boolean apiUrl) throws Exception {
        return new JSONArray(get(url, apiUrl).getBody());
    }

    public JSONObject deleteObject(String url) throws Exception {
        return deleteObject(url, true);
    }
    public JSONObject deleteObject(String url, boolean apiUrl) throws Exception {
        return new JSONObject(delete(url, apiUrl).getBody());
    }
}