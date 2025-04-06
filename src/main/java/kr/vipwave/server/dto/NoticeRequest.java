package kr.vipwave.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequest {
    private String title;
    private String content;
    private String url;
    private String staffNo;
}
