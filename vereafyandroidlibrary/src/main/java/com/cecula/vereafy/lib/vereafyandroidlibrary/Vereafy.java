/**This class handles Vereafy Integration on android, It contains four major methods - initialization,
 * completion, resend and getBalance*/

/**@author Blessing Kalu*/

package com.cecula.vereafy.lib.vereafyandroidlibrary;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vereafy {
    Logger logger = Logger.getAnonymousLogger();

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    private String result;

    private HttpURLConnection conn;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private StringBuilder resultStringBuilder;

    private String apiKey;

    /**The Vereafy Constructor accepts a single String parameter, the APIKEY*/
    public Vereafy(String apiKey) {
        this.apiKey = apiKey;
    }

    /**The initialization method is the beginning of the two factor authentication with
     *  Vereafy, it initializes a verification request. This method takes a single String
     *  parameter mobile, and returns a String response from the Vereafy initialization endpoint
     * @param mobile
     * @return response*/
    public String initialization(String mobile) {
        String returnString = "";

        try {

            JSONObject initializeJSON = new JSONObject();
            initializeJSON.put("mobile", mobile);

            returnString = makeRequestToServer(VereafyUtils.INITIALIZATION_ENDPOINT, initializeJSON.toString(),POST_METHOD);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong during initialization", e);
        }
        return returnString;
    }


    /**The completion method is the end of the two factor authentication with
     *  Vereafy, it completes a verification request. This method takes a String parameter pinRef and another String
     *  parameter token, and returns a String response from the Vereafy completion endpoint
     * @param
     * @return response*/
    public String completion(String pinRef, String token) {
        String returnString = "";

        try {

            JSONObject completionJSON = new JSONObject();
            completionJSON.put("token", token);
            completionJSON.put("pinRef", pinRef);

            returnString = makeRequestToServer(VereafyUtils.COMPLETION_ENDPOINT, completionJSON.toString(),POST_METHOD);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong during completion", e);
        }
        return returnString;
    }


    /**The resend method is used in case of  two factor authentication retry with
     *  Vereafy. This method takes a String parameter pinRef and another String
     *  parameter mobile, and returns a String response from the Vereafy resend endpoint
     * @param
     * @return response*/
    public String resend(String mobile, String pinRef) {
        String returnString = "";

        try {

            JSONObject resendJSON = new JSONObject();
            resendJSON.put("mobile", mobile);
            resendJSON.put("pinRef", pinRef);

            returnString = makeRequestToServer(VereafyUtils.RESEND_ENDPOINT, resendJSON.toString(),POST_METHOD);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong during Resending", e);
        }
        return returnString;
    }


    /**The getBalance method is used to get balance on a Vereafy acccount.
     * This method takes no parameter and returns a String response from the Vereafy balance endpoint
     * @param
     * @return response*/
    public String getBalance() {
        String returnString = "";

        try {

            returnString = makeRequestToServer(VereafyUtils.GET_BALANCE_ENDPOINT, null, GET_METHOD);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong while getting balance", e);
        }
        return returnString;
    }

    /*Here the request to server is made, parameters needed are the url, the parameters to be sent and the request method type*/
    private String makeRequestToServer(String url, String jsonParameters, String requestMethod) {

        try {
            URL requestUrl = new URL(url);

            conn = (HttpURLConnection) requestUrl.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);

            conn.setDoOutput(true);

            if(requestMethod == "POST") {
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(jsonParameters);
                writer.flush();
            }

            String line;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            resultStringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                resultStringBuilder.append(line);
            }

            result = resultStringBuilder.toString();


        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong while making request", e);
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {

                    logger.log(Level.SEVERE, "Something went wrong while closing output stream writer", e);
                }
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                    logger.log(Level.SEVERE, "Something went wrong while closing input stream reader", e);
                }
            }
        }

        return result;
    }
}
