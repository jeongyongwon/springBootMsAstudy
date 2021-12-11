package vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // null은 반환 x
public class ResponseOrder {
    private String productId;
    private Integer qty;

    private Integer unitPrice;
    private Integer totalPrice;

    private Date createdAt;

    private String orderId;
}
