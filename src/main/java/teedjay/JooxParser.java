package teedjay;

import org.joox.Match;
import org.w3c.dom.Document;

import static org.joox.JOOX.$;

/**
 * Testing the pretty cool https://code.google.com/p/joox/
 *
 * @author thore
 */
public class JooxParser {

    public String getNameSpace(Document document) {
        return $(document).namespaceURI();
    }

    public String getFirstTestDependency(Document document) {
        Match scoope = $(document).namespace("p", "http://maven.apache.org/POM/4.0.0").xpath("//p:dependency[p:scope = 'test']");
        for (Match m : scoope.each()) {
            return m.content(); // return first
        }
        return null;
    }

    public String appendComment(Document document) {
        $(document).find("resource").append("<!-- teedjay was here at the end of the books section -->");
        return $(document).namespace("p", "http://maven.apache.org/POM/4.0.0").xpath("//p:resources/p:resource[1]").content();
    }

}
