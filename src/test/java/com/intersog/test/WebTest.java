package com.intersog.test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: yurec
 * Date: 06.11.12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class WebTest {

    @Test
    public void test() throws IOException, SAXException {
        ServletRunner sr = null;
        sr = new ServletRunner(new File("src/webapp/WEB-INF/web.xml"));
        ServletUnitClient sc = sr.newClient();

        double distance = 7533.34;
        //
        //  INSERT
        //
        WebRequest request   = new GetMethodWebRequest("http://localhost:8080/insert" );
        request.setParameter( "city1", "New York" );
        request.setParameter( "city2", "Kiev" );
        request.setParameter( "distance", String.valueOf(distance) );//km

        WebResponse response = sc.getResponse( request );
        assertNotNull( "No response received", response );
        assertEquals( 200, response.getResponseCode());
        assertEquals( "content type", "text/plain", response.getContentType() );
        assertEquals( "inserted", response.getText() );

        //
        //  FIND
        //
        request = new GetMethodWebRequest("http://localhost:8080/find" );
        request.setParameter( "city1", "New York" );
        request.setParameter( "city2", "Kiev" );

        response = sc.getResponse( request );
        assertNotNull( "No response received", response );
        assertEquals( 200, response.getResponseCode());
        assertEquals( "content type", "text/plain", response.getContentType() );
        assertEquals( String.valueOf(distance), response.getText() );
    }
}
