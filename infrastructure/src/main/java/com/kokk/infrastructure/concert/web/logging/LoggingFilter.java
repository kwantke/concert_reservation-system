package com.kokk.infrastructure.concert.web.logging;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

  // 최대 로깅 바이트 (예: 64KB)
  private static final int MAX_LOG_BYTES = 64 * 1024;

  // 로그 샘플링 비율 (예: 10%만 바디 로그)
  private static final double SAMPLE_RATE = 0.1;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws ServletException, IOException {

    // JSON 등 텍스트 바디만 로깅, 멀티파트/바이너리는 스킵
    if (isBinaryOrMultipart(request)) {
      chain.doFilter(request, response);
      return;
    }

    ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request, MAX_LOG_BYTES);
    ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);

    long start = System.currentTimeMillis();
    try {
      chain.doFilter(req, res);
    } finally {
      long tookMs = System.currentTimeMillis() - start;

      // 요청 바디는 체인 통과 후에야 채워짐
      String reqBody = toBodyString(req.getContentAsByteArray(), getRequestCharset(req));
      String resBody = toBodyString(res.getContentAsByteArray(), getResponseCharset(res));


      log.info("[HTTP] {} {} {} {}ms\n headers: {}\n req body: {}\n status: {}\n res body: {}",
              request.getMethod(), request.getRequestURI(), request.getProtocol(), tookMs,
              summarizeHeaders(request), reqBody, res.getStatus(), resBody);

      // 꼭 원본으로 복사 (안 하면 클라이언트가 빈 응답 받음)
      res.copyBodyToResponse();
    }
  }

  private boolean isBinaryOrMultipart(HttpServletRequest request) {
    String ct = request.getContentType();
    if (ct == null) return false;
    ct = ct.toLowerCase();
    return ct.startsWith("multipart/") ||
            ct.startsWith("application/octet-stream") ||
            ct.startsWith("application/pdf") ||
            ct.startsWith("image/") ||
            ct.startsWith("audio/") ||
            ct.startsWith("video/");
  }

  private String getRequestCharset(HttpServletRequest req) {
    return req.getCharacterEncoding() != null ? req.getCharacterEncoding() : StandardCharsets.UTF_8.name();
  }
  private String getResponseCharset(HttpServletResponse res) {
    return res.getCharacterEncoding() != null ? res.getCharacterEncoding() : StandardCharsets.UTF_8.name();
  }

  private String toBodyString(byte[] bytes, String charset) {
    if (bytes == null || bytes.length == 0) return "";
    try { return new String(bytes, charset); }
    catch (Exception e) { return "[un-decodable body]"; }
  }

  private String summarizeHeaders(HttpServletRequest req) {
    Enumeration<String> names = req.getHeaderNames();
    Map<String, String> map = new LinkedHashMap<>();
    while (names.hasMoreElements()) {
      String n = names.nextElement();
      if ("authorization".equalsIgnoreCase(n) || "cookie".equalsIgnoreCase(n)) {
        map.put(n, "***");
      } else {
        map.put(n, req.getHeader(n));
      }
    }
    return map.toString();
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String p = request.getRequestURI();
    return p.startsWith("/swagger-ui") || p.startsWith("/v3/api-docs") || p.startsWith("/actuator/health");
  }
}