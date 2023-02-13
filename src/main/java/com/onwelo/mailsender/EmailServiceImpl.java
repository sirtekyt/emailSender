package com.onwelo.mailsender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(EmailDetails emailDetails){
        if(receiversToTab().length==0){
            return "You have to specify receivers";
        }
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(sender);
            helper.setTo(receiversToTab());
            helper.setSubject(emailDetails.getSubject());
            helper.setText(emailDetails.getMsgBody(), true);
            javaMailSender.send(mailMessage);
            return "Email has been sent";
        }
        catch (Exception e) {
            return "Email hasn't been sent";
        }
    }



    public String[] receiversToTab(){
        List<Receiver> receiversList=receiverRepository.findAll();
        String[] receivers=new String[receiversList.size()];
        if(receiversList.size()!=0){
            for(int i=0;i<receivers.length;i++){
                receivers[i]=receiversList.get(i).getEmail();
            }
        }
        return receivers;
    }
}
