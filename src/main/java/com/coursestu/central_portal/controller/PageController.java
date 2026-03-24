package com.coursestu.central_portal.controller;

import com.coursestu.central_portal.dto.TULoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    // ==========================================
    // ส่วนที่ 1: ระบบ Dashboard (มีการเช็ก Login)
    // ==========================================

    @GetMapping("/dashboard/student")
    public String getDashboardPage(HttpSession session, Model model) {
        // เช็กสิทธิ์กันคนแอบเข้า ถ้ายังไม่ล็อกอินให้เตะกลับหน้า Login
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null || !"student".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        // ส่งข้อมูลนักศึกษาไปแสดงบนหน้า HTML
        model.addAttribute("user", user);

        // วิ่งไปหาไฟล์ของคุณ: src/main/resources/templates/dashboard/student/dashboardstudent.html
        return "dashboard/student/dashboardstudent";
    }

    @GetMapping("/dashboard/teacher")
    public String getTeacherDashboardPage(HttpSession session, Model model) {
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null || !"teacher".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        
        // 🎯 เปลี่ยนเป็น home/teacher/... ครับ!
        return "home/teacher/teacher_my_courses"; 
    }


 // รับคำสั่งเมื่ออาจารย์กดเมนู "รายการทั้งหมด"
    @GetMapping("/dashboard/teacher/all-courses")
    public String getTeacherAllCoursesPage(HttpSession session, Model model) {
        
        // เช็กสิทธิ์กันคนแอบเข้าเหมือนเดิม
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null || !"teacher".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }
        
        // ส่งข้อมูลอาจารย์ไปโชว์ที่หน้า HTML
        model.addAttribute("user", user);
        
        // 🎯 สั่งให้เปิดไฟล์ teacher_all_courses.html 
        // (เช็ก Path โฟลเดอร์ให้ตรงกับของคุณด้วยนะครับ ถ้าอยู่ใน home/teacher ก็ตามนี้เลย)
        return "home/teacher/teacher_all_courses"; 
    }
    
    
    @GetMapping("/dashboard/teacher/course")
    public String getTeacherCourseDetailPage(@RequestParam(name="id", required=false) String courseId, HttpSession session, Model model) {
        TULoginResponse user = (TULoginResponse) session.getAttribute("user");
        if (user == null || !"teacher".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        model.addAttribute("courseId", courseId); // ส่งรหัสวิชาไปโชว์ใน Breadcrumb
        
        // ชี้ไปที่ไฟล์ HTML ที่คุณเพิ่งส่งมาครับ
        return "dashboard/teacher/dashboardteacher"; 
    }
    
    
    
    
    
    // ==========================================
    // ส่วนที่ 2: หน้าเว็บอื่นๆ ของคุณ (ของเดิม)
    // ==========================================

    @GetMapping("/home/student")
    public String getMyCoursesPage() {
        return "home/student/my_courses"; 
    }
    
    @GetMapping("/all-courses")
    public String getAllCoursesPage() {
        return "home/student/all_courses"; 
    }
    
    @GetMapping("/all-courses-p2")
    public String getAllCoursesPage2() {
        return "home/student/all_courses_p2"; 
    }
    
    @GetMapping("/assignment/submit")
    public String getSubmitAssignmentPage() {
        return "dashboard/student/submit"; 
    }
}