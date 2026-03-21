package com.coursestu.central_portal.controller;

import com.coursestu.central_portal.dto.TULoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/dashboard/student")
    public String studentDashboard(HttpSession session, Model model) {
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "dashboard/student/dashboardstudent";
    }

    @GetMapping("/dashboard/teacher")
    public String teacherDashboard(HttpSession session, Model model) {
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);

        // Mock data วิชาที่สอน
        List<Map<String, String>> courses = new ArrayList<>();

        Map<String, String> course1 = new HashMap<>();
        course1.put("code", "CS101");
        course1.put("name", "Introduction to Programming");
        course1.put("students", "45");
        courses.add(course1);

        Map<String, String> course2 = new HashMap<>();
        course2.put("code", "CS271");
        course2.put("name", "Artificial Intelligence Fundamentals");
        course2.put("students", "60");
        courses.add(course2);

        Map<String, String> course3 = new HashMap<>();
        course3.put("code", "CS301");
        course3.put("name", "Database Systems");
        course3.put("students", "38");
        courses.add(course3);

        model.addAttribute("courses", courses);

        return "dashboard/teacher/dashboardteacher";
    }
}