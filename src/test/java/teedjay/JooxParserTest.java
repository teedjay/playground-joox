package teedjay;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import static org.joox.JOOX.$;
import static org.testng.Assert.*;

public class JooxParserTest {

    private static final String examplePomFile = "/pim-pam-pom.xml";

    JooxParser jp;
    Document mavenPomFile;

    @BeforeMethod
    public void init() throws Exception {
        jp = new JooxParser();
        mavenPomFile = $(getClass().getResourceAsStream(examplePomFile)).document();
    }

    @Test
    public void verifyNamespace() {
        String value = jp.getNameSpace(mavenPomFile);
        assertEquals(value, "http://maven.apache.org/POM/4.0.0");
    }

    @Test
    public void verifyXpath() {
        String value = jp.getFirstTestDependency(mavenPomFile);
        assertEquals(value, "<dependency xmlns=\"http://maven.apache.org/POM/4.0.0\">\n" +
                "            <groupId>javax.servlet</groupId>\n" +
                "            <artifactId>servlet-api</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>");

    }

    @Test
    public void verifyAppend() {
        String value = jp.appendComment(mavenPomFile);
        assertEquals(value, "<resource xmlns=\"http://maven.apache.org/POM/4.0.0\">\n" +
                "                <directory>src/main/resources</directory>\n" +
                "                <filtering>true</filtering>\n" +
                "            <!-- teedjay was here at the end of the books section --></resource>");

    }

}