package com.outhelix.almis.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.outhelix.almis.dto.SystemMetrics;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class MetricsCacheService {
    private final Cache<Long, SystemMetrics> metricsCache = Caffeine.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

    public void addMetric(SystemMetrics metric) {
        metricsCache.put(System.currentTimeMillis(), metric);
    }

    public Map<Long, SystemMetrics> getAllMetrics (){
        return metricsCache.asMap();
    }

    public List<SystemMetrics> getMetricsForLastMinutes(int minutes) {
        long cutoff = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(minutes);
        return metricsCache.asMap().entrySet().stream()
                .filter(entry -> entry.getKey() >= cutoff)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
