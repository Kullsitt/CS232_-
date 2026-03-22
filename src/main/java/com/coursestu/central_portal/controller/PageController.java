package com.coursestu.central_portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // ใช้ @Controller เพื่อคืนค่าเป็นหน้า HTML (View)
public class PageController {

    // เมื่อ User พิมพ์ localhost:8080/dashboard/student
    @GetMapping("/dashboard/student")
    public String getDashboardPage() {
        /**
         * คืนค่าชื่อไฟล์ HTML (ไม่ต้องใส่ .html)
         * โดย Spring จะวิ่งไปหาที่ src/main/resources/templates/dashboard/student/dashboardstudent.html
         */
        return "dashboard/student/dashboardstudent";
    }
    
    
    @GetMapping("/home/student")
    public String getMyCoursesPage() {
   
        return "home/student/my_courses"; 
    
    }
    
    @GetMapping("/all-courses")
    public String getAllCoursesPage() {
        // อ้างอิง Path ไฟล์จาก templates/home/student/all_courses.html
        return "home/student/all_courses"; 
    }
    
    @GetMapping("/all-courses-p2")
    public String getAllCoursesPage2() {
        // ต้องระบุ Path ให้ถูกตามใน templates
        return "home/student/all_courses_p2"; 
    }
    
    
    @GetMapping("/assignment/submit")
    public String getSubmitAssignmentPage() {
        // ระบุตำแหน่งไฟล์ใน templates (สมมติว่าคุณวางไว้ใน dashboard/student/)
        return "dashboard/student/submit"; 
    }
    
    
 // 1. ตัวรับหน้า Login (GET) - สำหรับเปิดหน้าเว็บครั้งแรก
    @GetMapping({"/", "/login"})
    public String getLoginPage() {
        return "login"; // ต้องตรงกับชื่อไฟล์ login.html ใน templates
    }

    // 2. ตัวรับตอนกดปุ่ม Login (POST) - ตัวนี้แหละที่ขาดไป!
    @PostMapping("/login")
    public String processLogin() {
        // พอกดปุ่มแล้ว ให้ "Redirect" หรือส่ง User ไปหน้า My Courses ทันที
        return "redirect:/home/student"; 
    }
}
