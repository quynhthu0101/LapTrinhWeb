package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.User_22133060;

public interface IUserService {
    User_22133060 get(String id);

    void insert(User_22133060 user);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    User_22133060 findByUserName(String username);

    List<User_22133060> findAll();

    boolean changePassword(String username, String newPassword);

    void delete(int id) throws Exception;

    void update(User_22133060 user);
    
    User_22133060 login(String username, String password);
}
