package com.outhelix.almis.controller;

import com.outhelix.almis.dto.metrics.CpuMetrics;
import com.outhelix.almis.dto.metrics.MemoryMetrics;
import com.outhelix.almis.dto.metrics.SystemMetrics;
import com.outhelix.almis.service.SystemMetricsCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final SystemMetricsCollector systemMetricsCollector;

    @GetMapping("/cpu")
    public ResponseEntity<CpuMetrics> getCpuMetrics() {
        return ResponseEntity.ok(systemMetricsCollector.collectCpuMetrics());
    }

    @GetMapping("/memory")
    public ResponseEntity<MemoryMetrics> getMemoryMetrics() {
        return ResponseEntity.ok(systemMetricsCollector.collectMemoryMetrics());
    }
    @GetMapping("/all")
    public ResponseEntity<SystemMetrics> getAllMetrics() {
        return ResponseEntity.ok(systemMetricsCollector.collectAllMetrics());
    }
}