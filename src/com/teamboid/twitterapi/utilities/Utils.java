package com.teamboid.twitterapi.utilities;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * @author Aidan Follestad
 */
public class Utils {

    @SuppressWarnings("resource")
	public static String getBase64FromFile(File file) throws Exception {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("The file " + file.getName() + " is too large!");
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not read the entire file " + file.getName());
        }
        is.close();
        return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
    }

    public static String unescape(String str) {
        if(str == null) return null;
        return str.replace("&quot;", "\"").replace("&lt;", "<")
                .replace("&gt;", ">").replace("\\/","//");
    }

    public static String stripAnchor(String str) throws Exception {
        if(!str.contains("<a")) return str;
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        return doc.getDocumentElement().getTextContent();
    }
}
