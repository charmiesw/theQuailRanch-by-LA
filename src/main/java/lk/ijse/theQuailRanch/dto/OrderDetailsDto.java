package lk.ijse.theQuailRanch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDetailsDto {
    private String orderId;
    private String sellStockId;
    private int quantity;
    private double unitPrice;
}
