package oasip.backend.Service;

import oasip.backend.DTOs.Event.EventDetailDto;
import oasip.backend.DTOs.User.UserCreateDto;
import oasip.backend.DTOs.User.UserDetailDto;
import oasip.backend.DTOs.User.UserListAllDto;
import oasip.backend.DTOs.User.UserUpdateDto;
import oasip.backend.Enitities.User;
import oasip.backend.Enum.UserRole;
import oasip.backend.Exception.ErrorResponse;
import oasip.backend.ListMapper;
import oasip.backend.repositories.EventRepository;
import oasip.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

//    private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 26, 1, 65536, 10);
    private Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    public List<UserListAllDto> getAllUser() {
        List<User> userList = userRepository.findAll(Sort.by("name").ascending());
//        System.out.println(userList);
        return listMapper.maplist(userList, UserListAllDto.class, modelMapper);
    }

    public UserDetailDto getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , userId + " Does not Exist !!!"));
        return modelMapper.map(user, UserDetailDto.class);
    }

    public ResponseEntity<?> createUser(UserCreateDto newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getName().contains("anonymousUser")){
            if(newUser.getRole().length()==0){
                newUser.setRole("student");
            }
            User user = modelMapper.map(newUser, User.class);
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));

            for(UserRole r : UserRole.values()){
                if(newUser.getRole().equals(r.toString()))
                    user.setRole(r);;
            }
            userRepository.saveAndFlush(user);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(HttpStatus.FORBIDDEN,"Access denied"));
    }

    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException( HttpStatus.NOT_FOUND , userId + " Does not Exist !!!"));
//        eventRepository.deleteAllByUser(user);
        userRepository.deleteById(userId);
    }

    public UserUpdateDto updateUser(UserUpdateDto updateUser, Integer userId) {
//        System.out.println(updateUser.getRole());
        if(updateUser.getRole().length() == 0){
            updateUser.setRole("student");
        }
        User newUser = modelMapper.map(updateUser,User.class);
        for(UserRole r : UserRole.values()){
            if(updateUser.getRole().equals(r.toString()))
                newUser.setRole(r);
        }
        User user = userRepository.findById(userId).map(o -> mapEvent(o, newUser)).orElseGet(() -> {
            newUser.setId(userId);
            return newUser;
        });
        userRepository.saveAndFlush(user);
        return modelMapper.map(user, UserUpdateDto.class);
    }
    private User mapEvent(User existingUser, User updateUser) {
        if (updateUser.getName() != null) {
            existingUser.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null) {
            existingUser.setEmail(updateUser.getEmail());
        }
        if (updateUser.getRole() != null) {
            existingUser.setRole(updateUser.getRole());
        }
        return existingUser;
    }
}

