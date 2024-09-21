package Services.impl;

import Dao.IUserDao;
import Dao.impl.UserDaoImpl;
import Models.UserMoles;
import Services.IUserService;

public abstract class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();
    public boolean register(String username, String password, String email, String fullname ) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        userDao.insert(new UserMoles(id, username, fullname,password, null));
        return true;
    }
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }
    @Override
    public void insert(UserMoles user) {
        userDao.insert(user);
    }

}
