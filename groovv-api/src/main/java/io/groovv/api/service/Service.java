package io.groovv.api.service;

import io.groovv.model.api.core.Persistable;
import java.io.Serializable;
import java.util.List;

public interface Service<ID extends Serializable, T extends Persistable<ID>> {

  /**
   * @param from
   * @param to
   * @return
   */
  List<T> list(int offset, int to);
}
