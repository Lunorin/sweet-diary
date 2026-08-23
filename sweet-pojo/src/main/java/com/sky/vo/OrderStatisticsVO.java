package com.sky.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrderStatisticsVO implements Serializable {
    //待接单数量
    private Integer toBeConfirmed;

    //制作中数量
    private Integer preparing;

    //待派送数量
    private Integer toBeDelivered;

    //派送中数量
    private Integer deliveryInProgress;
}
