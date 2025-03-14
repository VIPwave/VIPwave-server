package kr.vipwave.server.repository;

import kr.vipwave.server.domain.ChartType;
import kr.vipwave.server.domain.OneClick;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OneClickRepository extends JpaRepository<OneClick, Long> {
    @EntityGraph(attributePaths = "links")
    OneClick findByPlatformId(Long platformId);

    @EntityGraph(attributePaths = {"platform", "links"})
    List<OneClick> findByChartType(ChartType chartType);
}
