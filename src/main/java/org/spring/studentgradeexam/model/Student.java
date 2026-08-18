package org.spring.studentgradeexam.model;

import java.util.List;

public record Student(String id,String firstName,String lastName,List<Grade> grades) {
}
