package io.groovv.model.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Models {


  private static final String PREFIX = "io.groovv.model.api";

  private static final String[] SCANNED_PACKAGES = {
      "core",
      "accounts",
      "registrations"
  };

  public static String[] getScannedPackages() {
    return Arrays.stream(SCANNED_PACKAGES).map(pkg -> PREFIX + "." + pkg).toList()
        .toArray(new String[0]);
  }


}
