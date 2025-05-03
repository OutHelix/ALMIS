package com.outhelix.almis.dto.metrics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemoryMetrics {
    private long totalMemory;
    private long availableMemory;
    private long usedMemory;
    private double usagePercentage;
}
