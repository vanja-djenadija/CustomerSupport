package com.example.customersupport.services;

import com.example.customersupport.beans.MessageBean;
import com.example.customersupport.dao.MessageDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class MessageService implements Serializable {

    private static final String sender = "webshop.pin.ip@gmail.com";
    private static final String password = "efpvjevsupegudmq";
    private static final String host = "smtp.gmail.com";
    private static final String port = "587";

    public List<MessageBean> getMessages() {
        return MessageDAO.getAll();
    }

    public void sendMessage(String userEmail, String content) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("[WebShop] Customer Support Reply");
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public MessageBean getMessage(int id) {
        return MessageDAO.getById(id);
    }

    public void changeMessageStatus(MessageBean messageBean) {
        MessageDAO.changeMessageStatus(messageBean.getId(), messageBean.isRead());
    }
}