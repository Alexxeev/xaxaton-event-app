package org.xaxaton.event_app.dto;

public record Debt(
        MemberDTO creditor,
        long sum
) { }
