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

        try {
            TULoginResponse tuResponse = tuAuthService.authenticate(username, password);

            if (tuResponse != null && tuResponse.isStatus()) {
                session.setAttribute("user", tuResponse);
                session.setAttribute("role", tuResponse.getTu_status());

                // นักศึกษา = username ตัวเลข 10 หลัก
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