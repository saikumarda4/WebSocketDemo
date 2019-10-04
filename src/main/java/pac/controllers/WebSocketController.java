package pac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }
    @GetMapping("/remote/{token}/{message}")
    public void remote(@PathVariable String token, @PathVariable String message) {
    	System.out.println(message);
    	onReceivedMesage(token, message);
    }

    @MessageMapping("/send/message/{token}")
    public void onReceivedMesage(@DestinationVariable String token, String message){
    	System.out.println("0987654321");
        this.template.convertAndSend("/chat/"+ token,message);
    }
}
