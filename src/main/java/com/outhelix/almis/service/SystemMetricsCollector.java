package com.outhelix.almis.service;

import com.outhelix.almis.dto.metrics.CpuMetrics;
import com.outhelix.almis.dto.metrics.StorageMetrics;
import com.outhelix.almis.dto.metrics.MemoryMetrics;
import com.outhelix.almis.dto.metrics.SystemMetrics;
import com.outhelix.almis.service.metrics.CpuMetricsService;
import com.outhelix.almis.service.metrics.MemoryMetricsService;
import com.outhelix.almis.service.metrics.StorageMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemMetricsCollector {
    private final CpuMetricsService cpuService;
    private final MemoryMetricsService memoryService;
    private final StorageMetricsService StorageService;

    public CpuMetrics collectCpuMetrics() {
        return cpuService.collectCpuMetrics();
    }

    public MemoryMetrics collectMemoryMetrics() {
        return memoryService.collectMemoryMetrics();
    }

    public List<StorageMetrics> collectStorageMetrics() {
        return StorageService.collectStorageMetrics();
    }

    @Cacheable(value = "system", key = "'system::all'")
    public SystemMetrics collectAllMetrics() {
        return SystemMetrics.builder()
                .cpu(cpuService.collectCpuMetrics())
                .memory(memoryService.collectMemoryMetrics())
                .disk(StorageService.collectStorageMetrics())
                .build();
    }
}
