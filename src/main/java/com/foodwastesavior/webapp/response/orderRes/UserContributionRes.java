package com.foodwastesavior.webapp.response.orderRes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserContributionRes {
    private Long orderAmounts;
    private Long savedMoney;
}
