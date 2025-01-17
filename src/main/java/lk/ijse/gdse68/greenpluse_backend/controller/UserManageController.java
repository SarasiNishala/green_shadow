package lk.ijse.gdse68.greenpluse_backend.controller;


import lk.ijse.gdse68.greenpluse_backend.dto.AuthDTO;
import lk.ijse.gdse68.greenpluse_backend.dto.ResponseDTO;
import lk.ijse.gdse68.greenpluse_backend.dto.UserDTO;
import lk.ijse.gdse68.greenpluse_backend.exception.NotFoundException;
import lk.ijse.gdse68.greenpluse_backend.service.impl.UserServiceImpl;
import lk.ijse.gdse68.greenpluse_backend.util.JwtUtil;
import lk.ijse.gdse68.greenpluse_backend.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/user")
@CrossOrigin
public class UserManageController {

    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserManageController(JwtUtil jwtUtil, UserServiceImpl userService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> registerUser(
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("role") String role
    ) {
        System.out.println("came");
        try {
            //handle prof.pic
//            String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePicture);

            UserDTO userDTO = new UserDTO();

            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setRole(role);

            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                case VarList.Forbidden -> {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(new ResponseDTO(VarList.Forbidden, "You must be a staff member to register", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody UserDTO userDTO) {
        System.out.println("1");
        UserDTO loadedUser = userService.loadUserDetailsByUsername(userDTO.getEmail(),userDTO.getPassword());
        if (loadedUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDTO(VarList.Conflict, "Wrong Email Or password..! ", null));
        }else {
            // Check user role
            if ("MANAGER".equals(loadedUser.getRole())) {
                // Handle manager login
                String token = jwtUtil.generateToken(loadedUser);
                if (token == null || token.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
                }

                AuthDTO authDTO = new AuthDTO();
                authDTO.setEmail(loadedUser.getEmail());
                authDTO.setToken(token);
                authDTO.setRole("MANAGER");
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDTO(VarList.Created, "Manager Login Success", authDTO));
            } else if ("ADMINISTRATIVE".equals(loadedUser.getRole())) {
                // Handle admin login
                String token = jwtUtil.generateToken(loadedUser);
                if (token == null || token.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
                }

                AuthDTO authDTO = new AuthDTO();
                authDTO.setEmail(loadedUser.getEmail());
                authDTO.setToken(token);
                authDTO.setRole("ADMINISTRATIVE");

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDTO(VarList.Created, "Administrative Login Success", authDTO));
            } else if ("SCIENTIST".equals(loadedUser.getRole())) {
                // Handle scientist login
                String token = jwtUtil.generateToken(loadedUser);
                if (token == null || token.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(new ResponseDTO(VarList.Conflict, "Authorization Failure! Please Try Again", null));
                }
                AuthDTO authDTO = new AuthDTO();
                authDTO.setEmail(loadedUser.getEmail());
                authDTO.setToken(token);
                authDTO.setRole("SCIENTIST");

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ResponseDTO(VarList.Created, "Scientist Login Success", authDTO));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ResponseDTO(VarList.Forbidden, "Others cannot login", null));
            }
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser
            (@PathVariable ("id") int id,
             @RequestPart("password")String updatePassword){

        try {
            userService.updateUserPassword(id, updatePassword);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser(@PathVariable ("id") int userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("id") int userId){
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
