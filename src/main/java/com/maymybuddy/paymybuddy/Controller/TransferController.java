package com.maymybuddy.paymybuddy.Controller;

import com.maymybuddy.paymybuddy.Entity.Transfer;
import com.maymybuddy.paymybuddy.Entity.User;
import com.maymybuddy.paymybuddy.Manager.transfer.TransferManager;
import com.maymybuddy.paymybuddy.Manager.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TransferController {

    @Autowired
    UserManager userManager;
    @Autowired
    TransferManager transferManager;

    @GetMapping("/home")
    public String home(Model model){
        // initiate a new transfer and get connection list
        Transfer transfer = new Transfer();
        List<User> users = userManager.findUserByConnectionFromUserSession();
        model.addAttribute("users", users);
        model.addAttribute("transfer", transfer);
        model.addAttribute("transfers", transferManager.findBySenderFromUserSession());
        return "transfer/home";
    }

    @PostMapping("/home")
    public String submitForm(@ModelAttribute @Valid Transfer transfer, BindingResult bindingResult, Model model){
        List<User> users = userManager.findUserByConnectionFromUserSession();
        // validate the form and proceed to processing
        if(bindingResult.hasErrors() == false){
            model.addAttribute("confirm", transferManager.save(transfer));
        }
        model.addAttribute("users", users);
        model.addAttribute("transfers", transferManager.findBySenderFromUserSession());
        return "transfer/home";
    }

}
