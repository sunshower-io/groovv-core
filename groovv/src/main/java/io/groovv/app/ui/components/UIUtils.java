package io.groovv.app.ui.components;

import io.sunshower.arcus.identicon.Jdenticon;
import io.sunshower.lang.common.encodings.Encoding;
import io.sunshower.lang.common.encodings.Encodings;
import io.sunshower.lang.common.encodings.Encodings.Type;
import lombok.val;

public class UIUtils {

  static final Encoding encoding = Encodings.create(Type.Base64);

  public static String base64Svg(Object o) {
    val svg = Jdenticon.toSvg(o);
    return String.format("data:image/svg+xml;base64,%s", encoding.encode(svg));
  }
}
