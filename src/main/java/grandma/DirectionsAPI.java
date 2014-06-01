package grandma;

import grandma.direction.Response;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Date;

/**
 * User: denise
 * Date: 6/1/14
 * Time: 2:36 PM
 */
public class DirectionsAPI {

    private final String apiKey;

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/directions/json";

    private final HttpClient client = new HttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DirectionsAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public Response getTimeToDestination(String startLocation, String endLocation) {
        HttpMethod method = new GetMethod(BASE_URL);
        method.setQueryString(new NameValuePair[]{new NameValuePair("origin", startLocation),
                new NameValuePair("destination", endLocation),
                new NameValuePair("sensor", "false"),
                new NameValuePair("mode", "driving"),
                new NameValuePair("alternatives", "true"),
                new NameValuePair("departure_time", Long.toString(new Date().getTime())),
                new NameValuePair("key", apiKey)
        });

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            Response parsedResponse = objectMapper.readValue(method.getResponseBody(), Response.class);
            return parsedResponse;

        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return new Response();
    }
}
