package ImageHoster.controller;

import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //This controller method is called when the request pattern is of type 'users/registration'
    //This method declares User type and UserProfile type object
    //Sets the user profile with UserProfile type object
    //Adds User type object to a model and returns 'users/registration.html' file
    @RequestMapping("users/registration")
    public String registration(Model model) {
        //Complete the method
        User newUser = new User();
        model.addAttribute("User",newUser);
        return "users/registration";
    }

    //This controller method is called when the request pattern is of type 'users/registration' and also the incoming request is of POST type
    //This method calls the business logic and after the user record is persisted in the database, directs to login page
    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(User user,Model model) {
        //Complete the method
        String password = user.getPassword();
        boolean strongPassword = userService.checkStrongPassword(password);

        System.out.println(strongPassword);

        if(strongPassword == true){
            userService.registerUser(user);
            return "redirect:/users/login";
        }else{
            String error = "Password must contain atleast 1 alphabet, 1 number & 1 special character";
            User user1 = new User();
            model.addAttribute("User",user1);
            model.addAttribute("passwordTypeError",error);
            return "/users/registration";
        }
    }

    //This controller method is called when the request pattern is of type 'users/login'
    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    //This controller method is called when the request pattern is of type 'users/login' and also the incoming request is of POST type
    //The return type of the business logic is changed to User type instead of boolean type. The login() method in the business logic checks whether the user with entered username and password exists in the database and returns the User type object if user with entered username and password exists in the database, else returns null
    //If user with entered username and password exists in the database, direct to user homepage displaying all the images in the application
    //If user with entered username and password does not exist in the database, redirect to the same login page
    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session) {
        //Complete the method
        User user1 = userService.login(user);
        if(user1 != null){
            session.setAttribute("loggeduser",user1);
            return "redirect:/images";
        }else{
            return  "users/login";
        }
    }

    @RequestMapping(value = "users/logout",method = RequestMethod.POST)
    public String logoutUser(HttpSession session){
        session.invalidate();
        return "index";
    }
}
