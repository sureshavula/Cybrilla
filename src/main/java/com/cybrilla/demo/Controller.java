package com.cybrilla.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cybrilla")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private Service service;

    @PostMapping("/create/shortUrl")
    protected ResponseEntity createShortUrl(@PathVariable("longUrl") String longUrl, @RequestHeader(value = "Authorization") String auth) throws Exception{
        String shortUrl = service.createShortUrl(longUrl);
        if(shortUrl!=null) {
            ShortUrl shortUrlObject = new ShortUrl();
            shortUrlObject.setShortUrl(shortUrl);
            return new ResponseEntity<>(shortUrlObject, HttpStatus.OK);
        }else{
            Error error =new Error();
            error.setMessage("SOMETHING WENT WRONG");
            error.setTimestamp(System.currentTimeMillis());
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/originalUrl")
    protected ResponseEntity getOriginalUrl(@PathVariable("shortUrl") String shortUrl, @RequestHeader(value = "Authorization") String auth) throws Exception{
        String longUrl = service.getLongUrl(shortUrl);
        if(longUrl!=null) {
            LongUrl longUrlObject = new LongUrl();
            longUrlObject.setLongUrl(longUrl);
            return new ResponseEntity<>(longUrlObject, HttpStatus.OK);
        }else{
            Error error =new Error();
            error.setMessage("SOMETHING WENT WRONG");
            error.setTimestamp(System.currentTimeMillis());
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
