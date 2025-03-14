package kr.vipwave.server.controller;

import kr.vipwave.server.dto.OneClickRequest;
import kr.vipwave.server.dto.OneClickResponse;
import kr.vipwave.server.service.OneClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/one-click")
public class OneClickRestController {
    private final OneClickService oneClickService;

    @PostMapping
    public ResponseEntity<OneClickResponse> createOneClick(@RequestBody OneClickRequest oneClickRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(oneClickService.createOneClick(oneClickRequest));
    }

    @GetMapping
    public ResponseEntity<Map<String, List<OneClickResponse>>> getOncClickByChart(@RequestParam(required = false) String chartType) {
        return ResponseEntity.ok(oneClickService.getOneClickList(chartType));
    }

    @GetMapping("/{platformId}")
    public ResponseEntity<OneClickResponse> getOncClick(@PathVariable Long platformId) {
        return ResponseEntity.ok(oneClickService.getOneClick(platformId));
    }
}
