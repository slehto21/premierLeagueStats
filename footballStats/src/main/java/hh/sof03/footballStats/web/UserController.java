package hh.sof03.footballStats.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.footballStats.domain.SignUpForm;
import hh.sof03.footballStats.domain.User;
import hh.sof03.footballStats.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String addUser(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "signup";
	}

	// Käyttäjän tallentaminen
	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {// Validation errors
			if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) { // Vastaavatko annetut salasanat
																					// toisiaan
				String pwd = signUpForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setEmail(signUpForm.getEmail());
				newUser.setUsername(signUpForm.getUsername());
				newUser.setRole(signUpForm.getRole());
				if (userRepository.findByUsername(signUpForm.getUsername()) == null) { // Onko käyttäjä jo olemassa
					userRepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

}
