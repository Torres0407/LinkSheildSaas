package com.mms4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class LinkShieldUI {
    public static void main(String[] args) {
        SpringApplication.run(LinkShieldUI.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "<html><body style='text-align:center; font-family:sans-serif; padding-top:50px;'>" +
               "<h1>🛡️ LinkShield SaaS Live</h1>" +
               "<input type='text' placeholder='Paste URL here' style='padding:10px; width:300px;'>" +
               "<button style='padding:10px; background:green; color:white;'>Shorten</button>" +
               "</body></html>";
    }
}
