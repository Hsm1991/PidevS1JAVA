/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author saif
 */
public class twilioSaif {
    public static final String sid = "AC7975df3a9b093a762d7a1ff4b02a54ab";
    public final static String token = "c97235bd540a94d89bceff98b215619d";
    public static void sendSMS(String text){
        Twilio.init(sid,token);
        
        Message msg = Message.creator(new PhoneNumber("+21699202178"),new PhoneNumber("+21628019294"), text).create();
        System.out.println(msg.getSid());
         
        
        
    }
    
}
