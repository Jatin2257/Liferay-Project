package com.helpdesk.rest.client.function;

import javax.annotation.Generated;

/**
 * @author Jatin
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}