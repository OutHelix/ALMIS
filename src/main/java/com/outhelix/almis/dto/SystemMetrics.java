package com.outhelix.almis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemMetrics {
    private double cpuUsage;
    private MemoryUsage memoryUsage;
}