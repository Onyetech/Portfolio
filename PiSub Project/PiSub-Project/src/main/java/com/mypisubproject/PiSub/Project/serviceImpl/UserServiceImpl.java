package com.mypisubproject.PiSub.Project.serviceImpl;

import com.mypisubproject.PiSub.Project.dto.UpdateUserDto;
import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.model.Wallet;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.repository.WalletRepository;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public User createUser(User user) {

        Wallet wallet = new Wallet();

        user.setPassword(passwordEncode.encode(user.getPassword()));
        user.setUserRole("ROLE_USER");
        user.setWallet(wallet);

        userRepo.save(user);

        wallet.setUser(user);
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setWalletAddress(wallet.getWalletAddress());

        walletRepo.save(wallet);

        return user;
    }

    @Override
    public boolean checkEmail(String email) {

        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean checkUniqueName(String uniqueName) {
        return userRepo.existsByUniqueName(uniqueName);
    }

    @Override
    public User updateUserDetails(User user, @ModelAttribute UpdateUserDto updateUser) {
        user.setPassword(passwordEncode.encode(user.getPassword()));

//            user.setEmail(updateUser.getEmail());
//            user.setFirstName(updateUser.getFirstName());
//            user.setLastName(updateUser.getLastName());
//            user.setPhoneNumber(updateUser.getPhoneNumber());

            userRepo.save(user);

//            user.setEmail(updateUser.getEmail());
//            user.setFirstName(updateUser.getFirstName());
//            user.setLastName(updateUser.getLastName());
//            user.setPassword(updateUser.getPassword());
//            user.setPhoneNumber(updateUser.getPhoneNumber());
//        }

        return user;
    }

    @Override
    public Optional<User> existsByEmailOrUniqueName(String email, String uniqueName) {
        return userRepo.findByEmailOrUniqueName(email, uniqueName);
    }

    @Override
    public User authUserLogin(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password).orElse(null);
    }


}
