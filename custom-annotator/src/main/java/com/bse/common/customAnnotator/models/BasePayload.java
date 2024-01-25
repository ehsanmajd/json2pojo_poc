package com.bse.common.customAnnotator.models;


import java.util.List;


public abstract class BasePayload implements Payload {
    private List<OrderLine> orderLines;
}
