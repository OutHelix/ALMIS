package com.outhelix.almis.service.metrics;

import com.outhelix.almis.dto.metrics.StorageMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageMetricsService {
    private final SystemInfo systemInfo;

    @Cacheable(value = "storage", key = "#root.methodName")
    public List<StorageMetrics> collectStorageMetrics() {
        return systemInfo.getOperatingSystem().getFileSystem().getFileStores()
                .stream()
                .map(fs -> {
                    long totalSpace = fs.getTotalSpace();
                    long freeSpace = fs.getFreeSpace();

                    return StorageMetrics.builder()
                            .name(fs.getName())
                            .mount(fs.getMount())
                            .type(fs.getType())
                            .totalSpace(totalSpace)
                            .usedSpace(totalSpace - freeSpace)
                            .freeSpace(freeSpace)
                            .usagePercentage((totalSpace - freeSpace) * 100.0 / totalSpace)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
