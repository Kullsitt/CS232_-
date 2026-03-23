package com.coursestu.central_portal.controller;

import com.coursestu.central_portal.dto.TULoginResponse;
import com.coursestu.central_portal.service.TUAuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private TUAuthService tuAuthService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        // ✅ Mock teacher สำหรับทดสอบ
        if ("teacher".equals(username) && "1234".equals(password)) {
            TULoginResponse mockTeacher = new TULoginResponse();
            mockTeacher.setStatus(true);
            mockTeacher.setTu_status("ปกติ");
            mockTeacher.setDisplayname_th("อาจารย์ทดสอบ");
            mockTeacher.setDisplayname_en("Test Teacher");
            mockTeacher.setUsername("t12345");
            mockTeacher.setEmail("teacher@tu.ac.th");
            mockTeacher.setFaculty("คณะวิทยาศาสตร์และเทคโนโลยี");
            session.setAttribute("user", mockTeacher);
            session.setAttribute("role", "teacher");
            return "redirect:/dashboard/teacher";
        }

        try {
            TULoginResponse tuResponse = tuAuthService.authenticate(username, password);

            if (tuResponse != null && tuResponse.isStatus()) {
                session.setAttribute("user", tuResponse);
                session.setAttribute("role", tuResponse.getTu_status());

                if (tuResponse.getUsername().matches("\\d{10}")) {
                    return "redirect:/dashboard/student";
                } else {
                    return "redirect:/dashboard/teacher";
                }
            }

        } catch (Exception e) {
            System.out.println("=== Login Error ===");
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Username หรือ Password ไม่ถูกต้อง");
        }

        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}