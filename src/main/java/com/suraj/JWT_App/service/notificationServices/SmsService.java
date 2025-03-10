package com.suraj.JWT_App.service.notificationServices;

import com.suraj.JWT_App.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioConfig twilioConfig;

    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public void sendSms(String to, String message) {
        Message sms = Message.creator(
                new PhoneNumber(to), // Receiver's phone number
                new PhoneNumber(twilioConfig.getSmsNumber()), // Twilio phone number
                message
        ).create();

    }
}
