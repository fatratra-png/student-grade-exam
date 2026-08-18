package org.spring.studentgradeexam.model;

import java.math.BigDecimal;

public record Grade(String id, BigDecimal score, Student student) {
}
