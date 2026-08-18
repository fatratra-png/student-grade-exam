package org.spring.studentgradeexam.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Exam(String id,String title,Instant examDate,int coefficient,List<Grade> examGrades) {
    public BigDecimal getHighestGrade(){
        return this.examGrades.stream().map(Grade::score).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public List<Student> getStudentsWithGradeBetween(BigDecimal min,BigDecimal max){
        return this.examGrades.stream().filter(grade->grade.score().compareTo(min)>=0 && grade.score().compareTo(max)<=0).map(Grade::student).toList();
    }
}
