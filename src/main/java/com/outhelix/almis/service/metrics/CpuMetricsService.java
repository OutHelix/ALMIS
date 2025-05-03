package com.outhelix.almis.service.metrics;

import com.outhelix.almis.dto.metrics.CpuMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@Service
@RequiredArgsConstructor
public class CpuMetricsService {
    private final SystemInfo systemInfo;

    public CpuMetrics collectCpuMetrics() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor processor = hardware.getProcessor();

        return CpuMetrics.builder()
                .name(processor.getProcessorIdentifier().getName())
                .vendor(processor.getProcessorIdentifier().getVendor())
                .physicalCoreCount(processor.getPhysicalProcessorCount())
                .logicalCoreCount(processor.getLogicalProcessorCount())
                .systemLoad(processor.getSystemCpuLoad(1000) * 100)
                .loadPerCore(processor.getProcessorCpuLoad(1000))
                .temperature(hardware.getSensors().getCpuTemperature())
                .build();
    }
}
