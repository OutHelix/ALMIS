package com.outhelix.almis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryUsage {
    private long total;
    private long available;
    private long used;
    private double usagePercentage;
}