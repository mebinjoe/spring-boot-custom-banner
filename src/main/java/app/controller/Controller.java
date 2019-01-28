package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Map;
import app.service.UriService;

@RestController
public class Controller {

    @Autowired
    UriService uriService;

    @PostMapping(path="renderBanner", produces="text/plain")
    private void renderBanner(@RequestBody Map<String, String> payload) {
        if (payload != null){
            String text = payload.get("text");
            String font = payload.get("font");
            String uri = "https://devops.datenkollektiv.de/renderBannerTxt";

            try {
                uri = String.valueOf(uriService.appendUri(uri+"?text="+text, "font="+font));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            System.out.println(result);
        }
    }


}
