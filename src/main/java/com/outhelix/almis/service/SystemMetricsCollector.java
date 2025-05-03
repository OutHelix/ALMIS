package com.outhelix.almis.service;

import com.outhelix.almis.dto.metrics.CpuMetrics;
import com.outhelix.almis.dto.metrics.MemoryMetrics;
import com.outhelix.almis.dto.metrics.SystemMetrics;
import com.outhelix.almis.service.metrics.CpuMetricsService;
import com.outhelix.almis.service.metrics.MemoryMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemMetricsCollector {
    private final CpuMetricsService cpuService;
    private final MemoryMetricsService memoryService;

    public CpuMetrics collectCpuMetrics() {
        return cpuService.collectCpuMetrics();
    }

    public MemoryMetrics collectMemoryMetrics() {
        return memoryService.collectMemoryMetrics();
    }

    public SystemMetrics collectAllMetrics() {
        return SystemMetrics.builder()
                .cpu(cpuService.collectCpuMetrics())
                .memory(memoryService.collectMemoryMetrics())
                .build();
    }
}
