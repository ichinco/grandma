package grandma;

import com.twilio.sdk.TwilioRestClient;
import java.util.ArrayList;
import java.util.List;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * User: denise
 * Date: 6/1/14
 * Time: 1:17 PM
 */
public class MailerAPI {

    public final String accountSid;
    public final String authToken;

    private final String fromNumber;

    private final TwilioRestClient client;

    public MailerAPI(String accountSid, String authToken, String fromNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromNumber = fromNumber;
        this.client = new TwilioRestClient(accountSid, authToken);
    }

    public Message setMessage(String toNumber, String messageToSend) throws TwilioRestException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", messageToSend));
        params.add(new BasicNameValuePair("From", fromNumber));
        params.add(new BasicNameValuePair("To", toNumber));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        return messageFactory.create(params);
    }
}
