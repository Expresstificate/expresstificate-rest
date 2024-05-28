package com.expresstificate.controller;


import org.expresstificate.Expresstificate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetData {
  @PostMapping(value = "/generate", produces = "application/zip")
  public ResponseEntity<byte[]> getCertificate(@RequestBody Map<String, Object> json) throws Exception {
    byte[] data = Expresstificate.generateByJson(json);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"certificate.zip\"");

    return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(data);
  }
}
