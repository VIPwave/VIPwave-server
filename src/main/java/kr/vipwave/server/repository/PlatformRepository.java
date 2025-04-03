package kr.vipwave.server.repository;

import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    List<Platform> findByChartType(ChartType chartType);
}
