package com.example.progettocompleto.FileDiSistema;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
    public class JavaMail {
        static String text;
        static String oggetto;
        public static void sendMail(String recepient) throws Exception{
            Properties properties = new Properties();
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");
            String myAccountEmail = "aziendaturni@gmail.com";
            String myAccountPassword = "eifubdokixeaptfi";
            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail,myAccountPassword);
                }
            });
            Message message = prepareMessage(session,myAccountEmail,recepient);
            try {
                Transport.send(message);
                System.out.println("Mail inviata");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        private static Message prepareMessage(Session session,String myAccountEmail,String recepient){
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO,new InternetAddress((recepient)));
                message.setSubject(oggetto);
                message.setText(text);
                return message;
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        public void setTesto(String text){
            this.text = text;
        }
        public void setOggetto(String oggetto){
            this.oggetto = oggetto;
        }
    }