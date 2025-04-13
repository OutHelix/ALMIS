package com.outhelix.almis.controller;

import com.outhelix.almis.dto.MemoryUsage;
import com.outhelix.almis.dto.SystemMetrics;
import com.outhelix.almis.service.MetricsCacheService;
import com.outhelix.almis.service.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricsService metricsService;
    private final MetricsCacheService metricsCacheService;

    @GetMapping("/current")
    public ResponseEntity<SystemMetrics> getCurrentMetrics() {
        return ResponseEntity.ok(metricsService.getCurrentMetrics());
    }

    @GetMapping("/cpu")
    public ResponseEntity<Double> getCpuUsage() {
        return ResponseEntity.ok(metricsService.getCpuUsage());
    }

    @GetMapping("/memory")
    public ResponseEntity<MemoryUsage> getMemoryUsage() {
        return ResponseEntity.ok(metricsService.getMemoryUsage());
    }

    @GetMapping("/history")
    public ResponseEntity<Map<Long, SystemMetrics>> getCashedMetrics() {
        return ResponseEntity.ok(metricsCacheService.getAllMetrics());
    }

    @GetMapping("/history/last/{minutes}")
    public ResponseEntity<List<SystemMetrics>> getCashedMetricsForLastMinutes(@PathVariable int minutes) {
        return ResponseEntity.ok(metricsCacheService.getMetricsForLastMinutes(minutes));
    }

}