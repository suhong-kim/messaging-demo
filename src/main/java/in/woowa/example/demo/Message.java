package in.woowa.example.demo;


import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private Long shopId;
    private String reason;
    private List<String> list;

    public Message() {
    }

    public Message(Long shopId, String reason, List<String> list) {
        this.shopId = shopId;
        this.reason = reason;
        this.list = list;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getReason() {
        return reason;
    }

    public List<String> getList() {
        return list;
    }
}
