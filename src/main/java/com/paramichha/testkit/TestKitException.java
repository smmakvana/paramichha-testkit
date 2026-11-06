package com.paramichha.testkit;

public class TestKitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TestKitException(String message, Throwable cause) { super(message, cause); }
    public TestKitException(String message) { super(message); }
}
