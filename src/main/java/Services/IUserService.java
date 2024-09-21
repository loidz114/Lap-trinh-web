package Services;

import Models.UserMoles;

public interface IUserService {
    UserMoles login(String username,String password);
    UserMoles findByUserName(String username);
    void insert(UserMoles user);
    boolean register(String email, String password, String username, String fullname);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

}
