package kr.vipwave.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(200, "성공", data);
    }

    public static <T> RestResponse<T> created(T data) {
        return new RestResponse<>(201, "생성됨", data);
    }
}
