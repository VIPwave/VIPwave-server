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

    public static <T> RestResponse<T> success(T data, String message) {
        return new RestResponse<>(200, message, data);
    }

    public static <T> RestResponse<T> created(T data, String message) {
        return new RestResponse<>(201, message, data);
    }

    public static <T> RestResponse<T> noContent() {
        return new RestResponse<>(204, null, null);
    }
}
