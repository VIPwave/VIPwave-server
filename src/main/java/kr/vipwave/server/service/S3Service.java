package kr.vipwave.server.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class S3Service {
    @Value("${aws.s3.bucket}")
    private String bucket;
    private final S3Template s3Template;

    public URL generatePresignedUrl(String fileName) {
        return s3Template.createSignedPutURL(bucket, fileName, Duration.ofMinutes(2));
    }
}
