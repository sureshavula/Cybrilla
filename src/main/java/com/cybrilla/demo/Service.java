package com.cybrilla.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;


public class Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    @Autowired
    private Dao dao;

    public String createShortUrl(String longUrl) {
        String shortUrl = null;
        try {
            String randomString = generateRandomString(longUrl, 5);
             shortUrl = "bit.ly/" + randomString;
            dao.saveShortUrl(shortUrl, longUrl);
        }catch (Exception e){
            LOGGER.error("SOMETHING WENT WRONG WHILE CREATING SHORT URL");
        }
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        if (shortUrl != null) {
            String longUrl = dao.getLongUrl(shortUrl);
            return longUrl;
        } else
            return null;
    }

    public String generateRandomString(String url,int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(url.length() * Math.random());
            sb.append(url.charAt(index));
        }
        return sb.toString();
    }

      /*public static String generateRandomString() {
        String txId = null;
        UUID randomUUID = UUID.randomUUID();
        txId = randomUUID.toString();
        return txId;
    }*/
}
