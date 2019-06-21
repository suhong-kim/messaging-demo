package in.woowa.example.demo;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnsPublisher {
    private final Logger log = LoggerFactory.getLogger(SnsPublisher.class);

    private final AmazonSNSAsync amazonSNSAsync;
    private final ObjectMapper mapper;
    private final String arn;

    public SnsPublisher(AmazonSNSAsync amazonSNSAsync,
                        ObjectMapper mapper,
                        @Value("${sns.demo.arn}") String arn) {
        this.amazonSNSAsync = amazonSNSAsync;
        this.mapper = mapper;
        this.arn = arn;
    }

    public void publish(Message message) {
        String serializedMessage = serialize(message);
        log.info("Published Message {}", serializedMessage);
        amazonSNSAsync.publish(new PublishRequest()
            .withTopicArn(arn)
            .withMessage(serializedMessage));
    }

    private String serialize(Message message) {
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
