package com.suraj.JWT_App.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.sms.number}")
    private String smsNumber;   // Twilio phone number for SMS

    @Value("${twilio.whatsapp.number}")
    private String whatsappNumber;  // Twilio phone number for WhatsApp

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }
    @PostConstruct
    public void init() {
        System.out.println("Twilio SID: " + accountSid);
        System.out.println("Twilio Auth Token: " + authToken);
        System.out.println("Twilio Phone Number: " + smsNumber);
        System.out.println("Twilio WhatsApp Number: " + whatsappNumber);
    }
}
