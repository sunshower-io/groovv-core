package io.groovv.api.porcelain;

import io.groovv.model.api.core.Persistable;
import java.io.Serializable;

/**
 * there are two types of services:
 * 1. Plumbing
 * 2. Porcelain
 *
 * Plumbing services may be difficult or dangerous to use as they may
 * not support transactions, or require complex configuration and setup
 *
 * Porcelain APIs are easy and safe to use.  When they interact with a resource,
 * it should be transactionally
 *
 *
 * @param <T> the type of the underlying entity.  This is just a marker interface for now
 *
 */
public interface PorcelainApiService<T> {

}
