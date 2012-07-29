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
    
    /**
     * Performs a wildcard matching for the text and pattern 
     * provided.
     * 
     * @param text the text to be tested for matches.
     * 
     * @param pattern the pattern to be matched for.
     * This can contain the wildcard character '*' (asterisk).
     * 
     * @return <tt>true</tt> if a match is found, <tt>false</tt> 
     * otherwise.
     */
    public static boolean wildCardMatch(String text, String pattern)
    {
        // Create the cards by splitting
        String [] cards = pattern.split("\\*");

        // Iterate over the cards.
        for (String card : cards)
        {
            int idx = text.indexOf(card);
            
            // Card not detected in the text.
            if(idx == -1)
            {
                return false;
            }
            
            // Move ahead, towards the right of the text.
            text = text.substring(idx + card.length());
        }
        
        return true;
    }

    public static String unescape(String str) {
        if(str == null) return null;
        return str.replace("&quot;", "\"").replace("&lt;", "<")
                .replace("&gt;", ">").replace("\\/","//");
    }

    public static String stripAnchor(String str) {
        if(!str.contains("<a")) return str;
        try {
        	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        	return doc.getDocumentElement().getTextContent();
        } catch(Exception e) {
        	return str.substring(str.indexOf(">"));
        }
    }
}
