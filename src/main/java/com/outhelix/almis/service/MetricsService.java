package com.outhelix.almis.service;


import com.outhelix.almis.dto.MemoryUsage;
import com.outhelix.almis.dto.SystemMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

@Service
@RequiredArgsConstructor
public class MetricsService {
    private final SystemInfo systemInfo = new SystemInfo();

    public SystemMetrics getCurrentMetrics() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        return SystemMetrics.builder()
                .cpuUsage(getCpuUsage(hardware))
                .memoryUsage(getMemoryUsage())
                .build();
    }

    public double getCpuUsage() {
        return getCpuUsage(systemInfo.getHardware());
    }

    private double getCpuUsage(HardwareAbstractionLayer hardware) {
        CentralProcessor processor = hardware.getProcessor();
        return processor.getSystemCpuLoad(500) * 100;
    }

    public MemoryUsage getMemoryUsage() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();

        return MemoryUsage.builder()
                .total(total)
                .available(available)
                .used(total - available)
                .usagePercentage((total - available) * 100.0 / total)
                .build();
    }
}