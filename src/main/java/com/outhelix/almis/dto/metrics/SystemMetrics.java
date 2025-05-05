package com.outhelix.almis.dto.metrics;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SystemMetrics {
    private CpuMetrics cpu;
    private MemoryMetrics memory;
    private List<StorageMetrics> disk;
}