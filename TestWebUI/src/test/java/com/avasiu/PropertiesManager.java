package com.avasiu;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class PropertiesManager {
    private static JSONObject jsonObject = null;

    public static String getProperty(String key) {
        if (jsonObject == null)
        {
            JSONParser jsonParser = new JSONParser();
            try {
                FileReader input = new FileReader(PropertiesManager.class.getResource("/config.json").getFile());
                jsonObject = (JSONObject) jsonParser.parse(input);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.get(key).toString();
    }
}
