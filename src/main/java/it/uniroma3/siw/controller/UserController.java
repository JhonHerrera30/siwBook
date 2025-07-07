package it.uniroma3.siw.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import it.uniroma3.siw.DTO.UserInfoDTO;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;


@Controller
public class UserController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/profile")
	public String getPersonalArea(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		boolean oauth = false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "home";
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof OAuth2User) {
			oauth = true;
			model.addAttribute("oauth", oauth);
		}
		if (credentials == null) {
			return "redirect:/login";
		}
		User user = credentials.getUser();
		model.addAttribute("user", user);
		model.addAttribute("credentials", credentials);
		model.addAttribute("reviews", user.getReviews());
		return "user/personalArea"; 
	}

	@GetMapping("/user/edit")
	public String showEditCredentialsForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("credentials", credentials);
		return "user/updateUser"; // questo corrisponde al tuo edit-user.html
	}

	@PostMapping("/user/edit")
	public String updateCredentials(@ModelAttribute("credentials") Credentials formCredentials,
	                                @RequestParam("oldPassword") String oldPassword,
	                                @RequestParam("newPassword") String newPassword,
	                                @RequestParam("confirmPassword") String confirmPassword,
	                                @AuthenticationPrincipal UserDetails userDetails,
	                                HttpServletRequest request,
	                                HttpServletResponse response,
	                                RedirectAttributes redirectAttributes,
	                                Model model) {

	    boolean error = false;
	    boolean credentialsChange = false;

	    Credentials currentCredentials = credentialsService.getCredentials(userDetails.getUsername());
	    User currentUser = currentCredentials.getUser();
	    User formUser = formCredentials.getUser();

	    // === VALIDAZIONE USERNAME ===
	    if (formCredentials.getUsername() == null || formCredentials.getUsername().isBlank()) {
	        model.addAttribute("noUsername", "This field is required.");
	        error = true;
	    } else {
	        Credentials sameUsername = credentialsService.getCredentials(formCredentials.getUsername());
	        if (sameUsername != null && !sameUsername.getId().equals(currentCredentials.getId())) {
	            model.addAttribute("usernameAlredyInUse", "This username is already in use.");
	            error = true;
	        }
	    }

	    // === VALIDAZIONE DATI UTENTE ===
	    if (formUser.getName() == null || formUser.getName().isBlank()) {
	        model.addAttribute("nameError", "This field is required.");
	        error = true;
	    }

	    if (formUser.getSurname() == null || formUser.getSurname().isBlank()) {
	        model.addAttribute("surnameError", "This field is required.");
	        error = true;
	    }

	    if (formUser.getEmail() == null || formUser.getEmail().isBlank()) {
	        model.addAttribute("emailError", "This field is required.");
	        error = true;
	    } else if (userService.existsByEmailExceptCurrent(formUser.getEmail(), currentUser.getId())) {
	        model.addAttribute("emailAlredyInUseError", "This email is already in use.");
	        error = true;
	    }

	    if (error) {
	        model.addAttribute("genericError", "Something went wrong. Please try again.");
	        return "user/updateUser";
	    }
	    
	    // === AGGIORNA USERNAME SE CAMBIATO ===
	    if (!formCredentials.getUsername().equals(currentCredentials.getUsername())) {
	        currentCredentials.setUsername(formCredentials.getUsername());
	        credentialsChange = true;
	    }

	    // === CAMBIO PASSWORD SE RICHIESTO ===
	    if (newPassword != null && !newPassword.isBlank()) {
	        if (oldPassword == null || oldPassword.isBlank()) {
	            model.addAttribute("noPassword", "Current password is required to change it.");
	            model.addAttribute("genericError", "Something went wrong. Please try again.");
	            return "user/updateUser";
	        }

	        if (!passwordEncoder.matches(oldPassword, currentCredentials.getPassword())) {
	            model.addAttribute("passwordError", "The current password is incorrect.");
	            model.addAttribute("genericError", "Something went wrong. Please try again.");
	            return "user/updateUser";
	        }

	        if (newPassword.equals(oldPassword)) {
	            model.addAttribute("passwordChangeError", "The new password must be different from the current one.");
	            model.addAttribute("genericError", "Something went wrong. Please try again.");
	            return "user/updateUser";
	        }

	        if (!newPassword.equals(confirmPassword)) {
	            model.addAttribute("passwordMismatchError", "The new passwords do not match.");
	            model.addAttribute("genericError", "Something went wrong. Please try again.");
	            return "user/updateUser";
	        }

	        currentCredentials.setPassword(newPassword);
	        credentialsChange = true;
	    }

	    // === AGGIORNA INFO UTENTE SEMPRE ===
	    currentUser.setName(formUser.getName());
	    currentUser.setSurname(formUser.getSurname());
	    currentUser.setEmail(formUser.getEmail());

	    credentialsService.saveCredentials(currentCredentials);

	    if (credentialsChange) {
	        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	        return "changeSuccessful";
	    }

	    redirectAttributes.addFlashAttribute("successProfile", "Your profile has been updated successfully.");
	    return "redirect:/profile";
	}
	
	@GetMapping("admin/platformInfo")
	public String getPlatformInfo(Model model) {
        long bookCount = bookService.countBooks();
        long reviewCount = reviewService.countReviews();
        long authorCount = authorService.countAuthors();
        List<UserInfoDTO> users = credentialsService.getAllUserInfoDTO(); 

        model.addAttribute("bookCount", bookCount);
        model.addAttribute("reviewCount", reviewCount);
        model.addAttribute("authorCount", authorCount);
        model.addAttribute("users", users);

        return "admin/platformInfo";
    }
}
