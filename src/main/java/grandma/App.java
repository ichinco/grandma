package grandma;

import com.twilio.sdk.TwilioRestException;
import grandma.direction.Leg;
import grandma.direction.Response;
import grandma.direction.Route;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


/**
 * Hello world!
 *
 */
public class App 
{
    private final DirectionsAPI directions;
    private final MailerAPI mailer;
    private static final String TEXT = "%s via %s";

    private final String sendToNumber;
    private final String fromAddress;
    private final String toAddress;

    public App(String sendToNumber, String fromAddress, String toAddress) throws ConfigurationException {
        this.sendToNumber = sendToNumber;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;

        Configuration config = new PropertiesConfiguration("grandma.properties");
        directions = new DirectionsAPI(config.getString("google.apikey"));

        String fromNumber = config.getString("number.from");
        String twilioSid = config.getString("twilio.sid");
        String twilioToken = config.getString("twilio.token");
        mailer = new MailerAPI(twilioSid, twilioToken, fromNumber);
    }

    public void run() throws TwilioRestException {
//        Response response = directions.getTimeToDestination("65 East Leuning St, South Hackensack, NJ", "25 Fifth Avenue, Haskell, NJ");
        Response response = directions.getTimeToDestination(fromAddress, toAddress);
        for (Route r : response.getRoutes()) {
            if (r.getLegs().size() == 1) {
                Leg leg = r.getLegs().get(0);
                mailer.setMessage(sendToNumber, String.format(TEXT, leg.getDuration().getText(), r.getSummary()));
            }
        }
    }

    public static void main( String[] args ) throws TwilioRestException, ConfigurationException {
        App app = new App(args[0], args[1], args[2]);
        app.run();
    }
}
