package com.outhelix.almis.dto.metrics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageMetrics {
    private String name;
    private String mount;
    private String type;
    private long totalSpace;
    private long usedSpace;
    private long freeSpace;
    private double usagePercentage;
}
