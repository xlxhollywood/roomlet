package com.example.utils;


import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;

@Log4j2
public class SHA256Util {
    public static final String ENCRYPTION_KEY = "SHA-256";

    public static String encryptSHA256(String str) {
        String SHA = null;

        try {
            MessageDigest sh = MessageDigest.getInstance(ENCRYPTION_KEY);
            sh.update(str.getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(String.format("%02x", byteDatum & 0xff)); // 수정된 부분
            }
            SHA = sb.toString();
        } catch (Exception e) {
            log.error("encryptSHA256 ERROR: {}", e.getMessage(), e); // 예외 객체 추가
        }

        return SHA;
    }
}


