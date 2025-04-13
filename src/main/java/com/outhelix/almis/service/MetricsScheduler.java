package com.outhelix.almis.service;

import com.outhelix.almis.dto.SystemMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsScheduler {
    private final MetricsCacheService metricsCacheService;
    private final MetricsService metricsService;

    @Scheduled(fixedRate = 5000)
    public void collectAndStoreMetrics() {
        SystemMetrics metrics = metricsService.getCurrentMetrics();
        metricsCacheService.addMetric(metrics);
    }
}
