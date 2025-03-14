package kr.vipwave.server.repository;

import kr.vipwave.server.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
