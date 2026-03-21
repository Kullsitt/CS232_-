package com.coursestu.central_portal.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    private String courseId; 
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments;

    // --- Getter/Setter ---
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }
}