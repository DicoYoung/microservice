package com.example.provider8002.service;

import com.example.provider8002.domain.User;
import com.example.provider8002.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    // 注入UserRepository
    @Resource
    private UserRepository userRepository;

    //@Transactional(rollbackFor={Exception.class})
    @Transactional(noRollbackFor={Exception.class})
    public int insertUser(User user){
        int result = userRepository.insertUser(user);
        //事务测试
        User u = null;
        u.setId(3);
        return result;
    }

    @Override
    public User selectByUsername(String username){
        return userRepository.selectByUsername(username);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void insertGetKey(User user) {
        // 数据插入成功以后，Mybatis框架会将插入成功的数据主键存入到user对象中去
        userRepository.insertGetKey(user);
    }

    @Override
    public int update(User user) {
        return userRepository.update(user);
    }

    @Override
    public int delete(Integer id) {
        return userRepository.delete(id);
    }

    @Override
    public User findUserById(int id) {
        // TODO Auto-generated method stub
        return userRepository.findUserById(id);
    }
}
