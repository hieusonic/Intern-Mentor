package com.example.demo2.controller.user;

import com.example.demo2.entity.ResponseDTO;
import com.example.demo2.entity.user.Role;
import com.example.demo2.entity.user.User;
import com.example.demo2.service.user.RoleService;
import com.example.demo2.service.user.UserService;
import com.example.demo2.until.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    RoleService roleService;

    /*
    phương thức post thêm mới một user vào data base
     */
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO<?>> saveUser(@RequestBody User user) {

        if (!StringUtils.validate(user.getEmail())) { // kiểm tra điều kiện về tên của email
            return ResponseEntity.ok(new ResponseDTO<>("Địa chỉ email không hợp lệ", "404", "Failed"));
        }
        // kiểm tra xem địa chỉ email đã được sử dụng chưa ; kiểm tra trong database
        if (service.getOneByEmail(user.getEmail()) != null) {
            return ResponseEntity.ok(new ResponseDTO<>("Email này đã được sử dụng", "400", "Failed"));
        }
            /* kiểm tra trong database user về đối tượng roles .
             nếu rỗng thì tạo một List ; List này có dạng là một List các đối tượng role
             Logic ở đây là đầu tiên sẽ tìm user trong database xem có roles chưa
             nếu chưa thì tạo một List danh sách các roles có kiểu là Role là danh sách các quyền của user
             sau đó tìm trong database và hán cho role  các Role có tên là ROLE_USER ; ROLE_USER chứa các quyền mà user sẽ có
             gán role vào list để tạo ra một danh sách các quyền mà user sẽ có
             các bước trên chỉ mới tạo ra danh sách ; bước cuối là gán cái danh sách đó cho 1 user cụ thể
             */
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            List<Role> roles = new ArrayList<>(); // đây là List danh sách các việc của Roles
            // tìm kiếm trong data base các đối tượng Role có tên là "ROLE_USER" gán vào role
            Role role = roleService.findByName("ROLE_USER");
            roles.add(role);// thêm role vưà được tìm ra vào danh sách roles
            user.setRoles(roles);
        }
        boolean rs = service.save(user);

        if (!rs) {
            return ResponseEntity.ok(new ResponseDTO<>("Đăng kí không thành công", "400", "Failed"));
        }

        return ResponseEntity.ok(new ResponseDTO<>(true, "200", "Success"));
    }

    /*
    api đăng nhập;
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@RequestBody User user) {
        // tìm kiếm trong database User theo email và mat khau
        User rs = service.getOneByEmailAndPass(user);

        if (!StringUtils.validate(user.getEmail())) {
            return ResponseEntity.ok(new ResponseDTO<>("Địa chỉ email không hợp lệ", "404", "Failed"));
        }


        if (rs == null) {
            return ResponseEntity.ok(new ResponseDTO<>("Không tìm thấy tài khoản này", "404", "Failed"));
        }

        return ResponseEntity.ok(new ResponseDTO<>(rs, "200", "Success"));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<?>> updateUser(@RequestBody User user) {
        User u = service.updateUser(user);

        if (u == null) {
            return ResponseEntity.ok(new ResponseDTO<>("Cập nhật thất bại", "400", "Fail"));
        } else {
            return ResponseEntity.ok(new ResponseDTO<>(new User(u.getEmail(), u.getName(), u.getAddress(), u.getPhoneNumber()), "200", "Success"));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<User>> getMe() {
        // principal lấy thông tin của người dùng qua email ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // từ thông tin principal(email). tìm kiếm trong database tên email phù hợp vaf trả về thông tin user
        User user = service.getOneByEmail(principal.toString());
        return ResponseEntity.ok(
                new ResponseDTO<User>(
                        new User(user.getEmail(), user.getName(), user.getAddress(), user.getPhoneNumber()), "200", "Success"));
    }

}
