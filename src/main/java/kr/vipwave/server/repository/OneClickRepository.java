package kr.vipwave.server.repository;

import kr.vipwave.server.domain.OneClick;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneClickRepository extends JpaRepository<OneClick, Long> {
    @EntityGraph(attributePaths = "links")
    OneClick findByPlatformId(Long platformId);
}
