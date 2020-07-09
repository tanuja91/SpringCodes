package com.example.accessingdatamysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.accessingdatamysql.User;
import com.example.accessingdatamysql.UserService;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userService;
	
	
	
	  @RequestMapping (value="/login",method=RequestMethod.GET)
      public String showLoginPage(ModelMap model) {
		  Cred cred = new Cred();
			model.addAttribute("cred" , cred);
      return "login";
  }
  
  @RequestMapping (value="/login",method=RequestMethod.POST)
  public String showWelcomePage(@ModelAttribute("cred") Cred cred,ModelMap model) {
	  
	  String Email= cred.email;
	  String Password= cred.password;
	  System.out.println(Email);
	  System.out.println("Welcome");
	  LoginService service = new LoginService();
  boolean isValidateUser=service.validateUser(Email,Password);
  
  if (!isValidateUser) {
      model.put("errorMessage", "Invalid Credentials");
  return "login";
  }
  return "Index";
}
  
	@PostMapping("/Search")
	public String searchPage(Model model)
	{
		User user = new User();
		model.addAttribute("user" , user);
		return "form";
	}
	
	@PostMapping("/Submit")
	public String searchUser(@ModelAttribute("user") User user, ModelMap model)
	{
		System.out.println("Reached o Pos mehod");
		String vendor_name= user.getName();
		
		
		 List<User> newUser=userService.findByname(vendor_name);
		 System.out.println(newUser);
		model.addAttribute("list", newUser );
		
		return "welcome";	
		
	}
	
	@PostMapping("/Redirect")
	public String redirect()
	{
		System.out.println("in redirec mehod");
	return	"Index";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		System.out.println("Controller Got the message");
		return userService.findAll();
	}
	
	
	public List<User> findByName(String Name) {
		
	     return userService.findByname(Name);
}

}
