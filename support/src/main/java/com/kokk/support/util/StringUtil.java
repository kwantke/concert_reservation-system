package com.kokk.support.util;

import io.micrometer.common.util.StringUtils;

public class StringUtil {

  public static boolean nvl(String str) {
    return StringUtils.isBlank(str) || "null".equalsIgnoreCase(str.trim());
  }
}
