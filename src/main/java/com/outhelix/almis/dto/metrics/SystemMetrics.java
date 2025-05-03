package com.outhelix.almis.dto.metrics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemMetrics {
    private CpuMetrics cpu;
    private  MemoryMetrics memory;
}
