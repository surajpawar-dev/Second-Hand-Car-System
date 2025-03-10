package com.suraj.JWT_App.service.notificationServices;

import com.suraj.JWT_App.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private final TwilioConfig twilioConfig;

    public WhatsAppService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public void sendWhatsAppMessage(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + to),  // Ensure 'to' is prefixed with 'whatsapp:'
                new PhoneNumber("whatsapp:" + twilioConfig.getWhatsappNumber()), // Ensure 'From' is also prefixed
                messageBody
        ).create();

        System.out.println("Message SID: " + message.getSid());
        System.out.println("Status: " + message.getStatus());
        System.out.println("Error Code: " + message.getErrorCode());
        System.out.println("Error Message: " + message.getErrorMessage());
    }

}
