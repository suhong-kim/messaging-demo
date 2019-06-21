package in.woowa.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Profile("mock")
public class MockMessagingDemoController {


    private final QueueMessagingTemplate messagingTemplate;
    private final String eventName;

    public MockMessagingDemoController(QueueMessagingTemplate messagingTemplate,
                                       @Value("${sqs-event.demo-event-name}") String eventName) {
        this.messagingTemplate = messagingTemplate;
        this.eventName = eventName;
    }

    @PostMapping("/mock")
    public ResponseEntity<Void> publishMockMessage() {
        messagingTemplate.convertAndSend(eventName, new Message(
            1L,
            "ORDERED",
            Arrays.asList("sku-123", "sku-234")
        ));
        return ResponseEntity.ok().build();
    }

}
