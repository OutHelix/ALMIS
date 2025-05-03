package com.outhelix.almis.dto.metrics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpuMetrics {
    private String name;
    private String vendor;
    private int physicalCoreCount;
    private int logicalCoreCount;
    private double systemLoad;
    private double[] loadPerCore;
    private double temperature;
}
