package kr.vipwave.server.service;

import kr.vipwave.server.dto.NoticeRequest;
import kr.vipwave.server.dto.NoticeResponse;

import java.util.List;

public interface NoticeService {
    List<NoticeResponse> getNoticeList();

    NoticeResponse createNotice(NoticeRequest noticeRequest);
}
