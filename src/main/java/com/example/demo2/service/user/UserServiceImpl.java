package com.example.demo2.service.user;

import com.example.demo2.entity.user.User;
import com.example.demo2.repo.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    /*
    service để lưu user vao data base
    */
    @Override
    public boolean save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // ma hoa mat khau
            userRepo.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /*
  service để tra ve mot doi tương user co dia chi email  va mat khau da co trong database
   */
    @Override
    public User getOneByEmailAndPass(User user) {
        return userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
    /*
 service để tra ve mot doi tương user co dia chi email  da co trong database
  */
    @Override
    public User getOneByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        User u = userRepo.findByEmail(user.getEmail()); // tim trong data base doi tuong user có email trong data base
        if (u == null) {
            return null;
        } else {
            u.setAddress(user.getAddress() != null ? user.getAddress() : u.getAddress()); // tao dia chi moi
            if (user.getPassword() != null) {
                u.setPassword(passwordEncoder.encode(user.getPassword())); // tao mat khau moi
            }
            if (user.getPhoneNumber() != null) {
                u.setPhoneNumber(user.getPhoneNumber()); // tao so dien thoai moi
            }
            if (user.getUsername() != null) { // tao ten moi
                u.setName(user.getName());
            }
            try {
                userRepo.save(u);
                return u;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        System.out.println("main-mail " + username);
        if(user == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        return user;
    }

}
