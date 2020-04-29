package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailService;
    private JwtUtil jwtTokenUtil;

/*    @GetMapping("/hello1")
    public String hello1() {
        return "hello1";
    }*/

    //@GetMapping("/hello2")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello2() {
        return "hello2";
    }

    //@PostMapping(value = "/login", consumes = "application/json")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }


}
