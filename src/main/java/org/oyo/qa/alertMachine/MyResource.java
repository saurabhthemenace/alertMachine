package org.oyo.qa.alertMachine;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("sender/{number}/{message}")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws Exception 
     */
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("number") String number,@PathParam("message") String message ) throws Exception {
    	String cmd = "adb shell am start -a android.intent.action.SENDTO -d sms:"+number+" --es sms_body " + message +" --ez exit_on_sent true";
    	String cmdsend1 = "adb shell input keyevent 22";
    	String cmdsend2 = "adb shell input keyevent 66";
    	try {
            InputStream is = Runtime.getRuntime().exec(cmd).getInputStream();
            while (is.read() != -1) {}
            Thread.sleep(3000);
            is = Runtime.getRuntime().exec(cmdsend1).getInputStream();
            while (is.read() != -1) {}
            Thread.sleep(1000);
            is = Runtime.getRuntime().exec(cmdsend2).getInputStream();
            while (is.read() != -1) {}
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Test Pull Request");
        }
    	return "Info: "+ number + " and new " + message;
    }
    
    
    
    
}
