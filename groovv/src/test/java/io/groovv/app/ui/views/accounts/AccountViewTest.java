package io.groovv.app.ui.views.accounts;

import static org.junit.jupiter.api.Assertions.*;

import io.sunshower.arcus.identicon.Jdenticon;
import org.junit.jupiter.api.Test;

class AccountViewTest {


  @Test
  void ensureId() {
    System.out.println(Jdenticon.toSvg("hello"));
  }
}