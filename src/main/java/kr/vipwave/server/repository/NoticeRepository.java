package kr.vipwave.server.repository;

import kr.vipwave.server.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByNoticeOrderDesc();

    @Query("SELECT COALESCE(MAX(n.noticeOrder), 0) FROM Notice n")
    int findMaxNoticeOrder();
}
