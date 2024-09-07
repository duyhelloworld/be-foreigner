package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.configurations.SecurityHolder;
import vn.edu.huce.beforeigner.entities.core.User;
import vn.edu.huce.beforeigner.infrastructures.coremodule.abstracts.IUserService;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserInfoDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.mappers.UserMapper;
import vn.edu.huce.beforeigner.repositories.UserRepository;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepo;

    private UserMapper userMapper;

    @Override
    public List<UserDto> findAllUsers() {
        return userRepo.findAll()
            .stream()
            .map(u -> userMapper.toDto(u))
            .toList();
    }

    @Override
    public UserInfoDto getInfo() {
        User user = SecurityHolder.getUser();
        return userMapper.toInfoDto(user);
    }
}
