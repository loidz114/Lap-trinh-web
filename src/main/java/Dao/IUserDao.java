package Dao;

import Models.UserMoles;

import java.util.List;

public interface IUserDao {
    List<UserMoles> findAll();
    UserMoles findById(int id);
    UserMoles findByUserName(String username);
    void insert(UserMoles user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}
