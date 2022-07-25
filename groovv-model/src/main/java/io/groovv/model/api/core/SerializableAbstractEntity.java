package io.groovv.model.api.core;

import java.io.Serializable;

public class SerializableAbstractEntity<ID extends Serializable> extends
    AbstractEntity<ID> implements Serializable {

}
