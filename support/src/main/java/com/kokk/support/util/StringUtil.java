package com.kokk.support.util;


public class StringUtil {

  /**
   * 문자열이 null, 빈 문자열("") 또는 "null" 문자열일 경우 true 반환
   */
  public static boolean nvl(String str) {
    return isBlank(str) || "null".equalsIgnoreCase(str.trim());
  }

  /**
   * 문자열이 null, 빈 문자열(""), 또는 공백으로만 구성되어 있으면 true 반환
   */
  public static boolean isBlank(String str) {
    return str == null || str.trim().isEmpty();
  }
}