package com.foodwastesavior.webapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReviewReq {
    private Integer rating;
}
