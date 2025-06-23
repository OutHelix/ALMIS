package com.outhelix.almis.service.metrics;

import com.outhelix.almis.dto.metrics.MemoryMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@Service
@RequiredArgsConstructor
public class MemoryMetricsService {
    private final SystemInfo systemInfo;

    public MemoryMetrics collectMemoryMetrics() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory memory = hardware.getMemory();

        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();

        return MemoryMetrics.builder()
                .totalMemory(totalMemory)
                .availableMemory(availableMemory)
                .usedMemory(totalMemory - availableMemory)
                .usagePercentage((totalMemory - availableMemory) * 100.0 / totalMemory)
                .build();
    }
}
