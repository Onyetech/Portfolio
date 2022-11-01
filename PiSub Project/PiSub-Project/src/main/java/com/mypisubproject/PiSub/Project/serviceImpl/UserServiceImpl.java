package com.mypisubproject.PiSub.Project.serviceImpl;

import com.mypisubproject.PiSub.Project.model.User;
import com.mypisubproject.PiSub.Project.model.Wallet;
import com.mypisubproject.PiSub.Project.repository.UserRepository;
import com.mypisubproject.PiSub.Project.repository.WalletRepository;
import com.mypisubproject.PiSub.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

//        wallet.setId(newUser.getWallet().getId());
        wallet.setUser(user);
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setWalletAddress(wallet.getWalletAddress());

        walletRepo.save(wallet);

//        return userRepo.save(user);
        return user;
    }

    @Override
    public boolean checkEmail(String email) {

        return userRepo.existsByEmail(email);
    }

//    @Override
//    public User authUserLogin(String email, String password) {
//        return userRepo.findByEmailAndPassword(email, password).orElse(null);
//    }

}
