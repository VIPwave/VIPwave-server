package kr.vipwave.server.repository;

import kr.vipwave.server.domain.DeviceType;
import kr.vipwave.server.domain.OneClick;
import kr.vipwave.server.domain.OneClickLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OneClickLinkRepository extends JpaRepository<OneClickLink, Long> {
    List<OneClickLink> findByOneClick(OneClick oneClick);

    void deleteByOneClickAndDeviceType(OneClick oneClick, DeviceType deviceType);
}
