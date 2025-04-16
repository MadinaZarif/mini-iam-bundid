package mini_iam_bundid.demo.controller;

import jakarta.servlet.http.HttpSession;
import mini_iam_bundid.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @PostMapping("/switch-role")
    public String switchRole(@RequestParam String role, HttpSession session) {
        session.setAttribute("role", role);
        return "redirect:/dashboard";
    }
    @PostMapping("/admin/users/role")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRole(@RequestParam String username, @RequestParam String role) {
        userService.updateUserRole(username, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model, HttpSession session) {
        model.addAttribute("username", auth.getName());
        model.addAttribute("roles", auth.getAuthorities());

        // Определяем текущую роль из GrantedAuthority
        String currentRole = auth.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("GUEST");

        // Если пользователь переключил роль — используем её
        String switchedRole = (String) session.getAttribute("role");
        if (switchedRole != null) {
            model.addAttribute("role", switchedRole);
        } else {
            model.addAttribute("role", currentRole);
        }

        return "dashboard";
    }
    @PostMapping("/admin/users/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/users/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.addUser(username, password, role);
        return "redirect:/admin/users";
    }


}

