package in.woowa.example.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MessagingDemoController {

    private final SnsPublisher snsPublisher;

    public MessagingDemoController(SnsPublisher snsPublisher) {
        this.snsPublisher = snsPublisher;
    }

    @PostMapping("/sns")
    public ResponseEntity<Void> publishToSns() {
        snsPublisher.publish(new Message(
            1L,
            "ORDERED",
            Arrays.asList("sku-123", "sku-234")
        ));
        return ResponseEntity.ok().build();
    }

}
