package com.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class LinkShieldUI {

    // Stores shortCode -> longUrl
    private final Map<String, String> urlDatabase = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(LinkShieldUI.class, args);
    }

    // Homepage + form submission
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(@RequestParam(value = "longUrl", required = false) String longUrl) {

        String resultSection = "";

        if (longUrl != null && !longUrl.isEmpty()) {

            // Generate short code
            String shortCode = Integer.toHexString(longUrl.hashCode());

            // Save mapping
            urlDatabase.put(shortCode, longUrl);

            resultSection =
                    "<div style='margin-top:20px; padding:15px; background:#d4edda; border:1px solid #c3e6cb; color:#155724; border-radius:10px; display:inline-block;'>" +
                            "<strong>Success! Your Short Link:</strong><br>" +
                            "<a href='/" + shortCode + "' style='color:#1e7e34; font-weight:bold;'>" +
                            "http://localhost:8080/" + shortCode +
                            "</a>" +
                            "</div>";
        }

        return "<html>" +
                "<body style='text-align:center; font-family:Arial, sans-serif; padding-top:50px; background-color:#f4f7f6;'>" +
                "<div style='background:white; padding:40px; border-radius:15px; display:inline-block; box-shadow:0 4px 6px rgba(0,0,0,0.1);'>" +
                "<h1 style='color:#2c3e50;'>🛡️ LinkShield SaaS Live</h1>" +
                "<p style='color:#7f8c8d;'>Enter a long URL to generate a secure short link.</p>" +
                "<form method='POST' style='margin-top:20px;'>" +
                "<input type='url' name='longUrl' placeholder='https://example.com' required " +
                "style='padding:12px; width:350px; border:1px solid #bdc3c7; border-radius:5px; outline:none;'>" +
                "<button type='submit' style='padding:12px 25px; background:#27ae60; color:white; border:none; border-radius:5px; cursor:pointer; font-weight:bold;'>" +
                "Shorten Now" +
                "</button>" +
                "</form>" +
                resultSection +
                "</div>" +
                "</body>" +
                "</html>";
    }

    // Redirect short link
    @GetMapping("/{shortCode}")
    public String redirect(@PathVariable String shortCode) {

        String longUrl = urlDatabase.get(shortCode);

        if (longUrl != null) {
            return "<html><head>" +
                    "<meta http-equiv='refresh' content='0; URL=" + longUrl + "' />" +
                    "</head><body>" +
                    "<p>Redirecting...</p>" +
                    "</body></html>";
        }

        return "<h2>404 - Link not found</h2>";
    }
}
