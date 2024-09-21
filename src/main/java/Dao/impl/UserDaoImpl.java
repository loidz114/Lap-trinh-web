package Dao.impl;

import Configs.DBConnectMySQL;
import Dao.IUserDao;
import Models.UserMoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public abstract class UserDaoImpl extends DBConnectMySQL implements IUserDao {
    public Connection conn= null;
    public PreparedStatement ps=null;
    public ResultSet rs=null;

    public List<UserMoles> findAll()
    {
        String sql="select * from users";
        List<UserMoles> list= new ArrayList<>();
        try{
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next())
            {
                list.add(new UserMoles(rs.getInt("id")
                        ,rs.getString("username")
                        ,rs.getString("email")
                        ,rs.getString("fullname")
                        ,rs.getString("image")
                        ,rs.getString("password")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public UserMoles findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserMoles user = new UserMoles(rs.getInt("id")
                        ,rs.getString("username")
                        ,rs.getString("email")
                        ,rs.getString("fullname")
                        ,rs.getString("image")
                        ,rs.getString("password"));

                return user;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public UserMoles findById(int id)
    {
        String sql = "SELECT * FROM users WHERE id  = ? ";
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserMoles user = new UserMoles(rs.getInt("id")
                        ,rs.getString("username")
                        ,rs.getString("email")
                        ,rs.getString("fullname")
                        ,rs.getString("image")
                        ,rs.getString("password"));

                return user;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    public void insert(UserMoles user)
    {
        String sql="INSERT INTO users (id, username, email,fullname,image,password) VALUES(?,?,?,?,?,?)";
        try{
            conn =super.getDatabaseConnection();
            ps= conn.prepareStatement(sql);

            ps.setInt(1,user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3,user.getEmail());
            ps.setString(4, user.getFullname());
            ps.setString(5,user.getImage());
            ps.setString(6, user.getPassword());

            ps.executeUpdate();
        }catch (Exception e)
        {

        }
    }
    public static void main(String[] arg)
    {
        IUserDao userDao= new UserDaoImpl();
        try{
            System.out.println(userDao.findByUserName("loi"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
