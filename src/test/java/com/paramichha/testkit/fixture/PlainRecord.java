package com.paramichha.testkit.fixture;

/** A record with no constraint annotations — the "unannotated class" case. */
public record PlainRecord(int count, String label, Long id) {}
