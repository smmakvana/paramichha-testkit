package com.paramichha.testkit.foreign;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** Another library's annotation that happens to share jakarta's name, but has no value(). */
@Retention(RetentionPolicy.RUNTIME)
public @interface Min {}
