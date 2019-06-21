package in.woowa.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;


@Component
public class DemoConsumer {
    private final Logger log = LoggerFactory.getLogger(DemoConsumer.class);


    @SqsListener(value = "${sqs-event.demo-event-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consume(String message) {
//        log.info("ShopId : {}", message.getShopId());
//        log.info("Reason : {}", message.getReason());
//        message.getList().forEach(sku -> log.info("SkuId : {}", sku));
        log.info(message);
    }

    @SqsListener(value = "${sqs-event.demo-event-name-dead}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeDeadMessage(Message message) {
        log.info("ShopId : {}", message.getShopId());
        log.info("Reason : {}", message.getReason());
        message.getList().forEach(sku -> log.info("SkuId : {}", sku));
    }
}
