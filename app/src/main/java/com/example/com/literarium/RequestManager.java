package com.example.com.literarium;

import org.apache.commons.lang3.ClassUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * formats all the non-parsing-related XML requests involved in the application.
 */
public class RequestManager {

    public static String formatRequest(RequestType type, Object ... args) throws UnsupportedEncodingException {

        String req = "";

        String[] urlEncodedArgs = new String[args.length];
        for(int i = 0; i < args.length; ++i) {
            if(args[i] instanceof String)
                urlEncodedArgs[i] = URLEncoder.encode((String)args[i], java.nio.charset.StandardCharsets.UTF_8.toString());
            else if(ClassUtils.isPrimitiveOrWrapper(args[i].getClass()))
                urlEncodedArgs[i] = URLEncoder.encode(String.valueOf(args[i]), java.nio.charset.StandardCharsets.UTF_8.toString());
        }


        if(type == RequestType.GEO_REPORT)
            req = "http://192.168.1.7/literarium_api/geo_report.php?userid="+ URLEncoder.encode(urlEncodedArgs[0], java.nio.charset.StandardCharsets.UTF_8.toString());
        else if(type == RequestType.LOG_POSITION)
            req = "http://192.168.1.7/literarium_api/insert_geo_data.php?"
                    +"userid="+urlEncodedArgs[0]
                    +"&latitudine="+urlEncodedArgs[1]
                    +"&longitudine="+urlEncodedArgs[2]
                    +"&indirizzo="+urlEncodedArgs[3];

        return req;
    }

}