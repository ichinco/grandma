package grandma.direction;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * User: denise
 * Date: 6/1/14
 * Time: 3:11 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leg {

    private KeyValue duration;
    private KeyValue distance;

    public KeyValue getDuration() {
        return duration;
    }

    public void setDuration(KeyValue duration) {
        this.duration = duration;
    }

    public KeyValue getDistance() {
        return distance;
    }

    public void setDistance(KeyValue distance) {
        this.distance = distance;
    }
}
