package kr.vipwave.server.controller;

import kr.vipwave.server.dto.RestResponse;
import kr.vipwave.server.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {
    private final S3Service s3Service;

    @GetMapping("/presigned")
    public RestResponse<String> getPresignedUrl(@RequestParam String fileName) {
        return RestResponse.success(s3Service.generatePresignedUrl(fileName).toString());
    }
}
