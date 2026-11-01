package com.paramichha.testkit.fixture.scan;

import com.paramichha.datafactory.catalog.realworld.payment.Payment;
import com.paramichha.datafactory.catalog.realworld.registration.UserRegistration;
import com.paramichha.testkit.scan.Testables;

/** Marks catalog classes for TestableScanner tests — the catalog can't depend on TestKit. */
@Testables({UserRegistration.class, Payment.class})
public final class ScanTargets {
    private ScanTargets() {}
}
