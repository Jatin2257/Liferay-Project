package com.book.web.api;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class CommonApiUtil {
	
	private static final Log _log = LogFactoryUtil.getLog(CommonApiUtil.class);
	
	/*
	 * Common Method which called Api
	 * 
	 * @Param String apiUrl
	 * @Param String method - it's take method of Http like GET,POST ...
	 * @String requestbody - it's take json
	 */
	public static String callApi(String apiUrl, String method,String requestBody) {
        StringBuilder response = new StringBuilder();
        // Provide email and password for authentication api 
        String email = "test@liferay.com";
		String password = "123";
		String auth = email + ":" + password;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
		String authHeader = "Basic " + encodedAuth;
        
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", authHeader);

            if (requestBody != null && !requestBody.isEmpty()) {
                try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                    writer.write(requestBody);
                    writer.flush();
                }
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode()==204) {
                _log.info("API call successful");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }
                bufferedReader.close();
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
        } catch (Exception e) {
            _log.error("Error while calling API", e);
            return null;
        }

        return response.toString();
    }	
}
