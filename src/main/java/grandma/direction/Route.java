package grandma.direction;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * User: denise
 * Date: 6/1/14
 * Time: 2:53 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    private String summary;
    private List<Leg> legs;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
