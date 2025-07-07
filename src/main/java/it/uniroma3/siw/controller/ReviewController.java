package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private UserService userservice;
	
	
	@GetMapping("/user/review/add/{bookId}")
	public String addReview(@PathVariable("bookId")Long bookId, Model model) {
		Book book = this.bookService.findById(bookId); 
		Review review = new Review();
		review.setBook(book);
		model.addAttribute("book", book);
		model.addAttribute("review", review);
		return "user/formNewReview" ;
	}
	
	@PostMapping("/user/review/save/{bookId}")
	public String saveReview(@PathVariable("bookId")Long bookId,
								@Valid @ModelAttribute("review") Review review,
								BindingResult bindingResult, Model model,
								RedirectAttributes redirectAttributes) {
		Book book = this.bookService.findById(bookId); 
		if(bindingResult.hasErrors()) {
			review.setBook(book);
			model.addAttribute("book", book);
			model.addAttribute("review", review);
			model.addAttribute("genericError", "Something went wrong. Please try again.");
			return "user/formNewReview" ;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "redirect:login";
		}
		else {		
			User user = userservice.getCurrentUser();
			review.setUser(user);
			review.setBook(book);
			review.setDate(LocalDate.now());
			this.reviewService.save(review);
			redirectAttributes.addFlashAttribute("successSave", "Your review has been saved successfully!");
			return "redirect:/anyone/book/"+bookId;
		}	
	}
	
	@GetMapping("/user/review/delete/{reviewId}")
	public String deleteUserReview(@PathVariable("reviewId")Long reviewId,@RequestParam(required = false) String source, Model model, RedirectAttributes redirectAttributes) {
		Review review = this.reviewService.findById(reviewId);
		Long bookId = review.getBook().getId();
		User user = review.getUser();
		user.getReviews().remove(review);
		this.reviewService.deleteById(reviewId);
		if(source!=null) {
			redirectAttributes.addFlashAttribute("successDelete", "Your review has been deleted successfully!");
			return "redirect:/profile";
		}
		redirectAttributes.addFlashAttribute("successDelete", "Your review has been deleted successfully!");
		return "redirect:/anyone/book/"+ bookId;	
		}
	
	@GetMapping("/user/review/edit/{reviewId}")
	public String editReview(@PathVariable("reviewId")Long reviewId, @RequestParam(required = false) String source, Model model) {
		Review review = this.reviewService.findById(reviewId);
		model.addAttribute("review", review);
		if(source!=null) {
			model.addAttribute("source", source);
		}
		return "user/updateReview";
	}
	
	@PostMapping("user/review/update/{reviewId}")
	public String updateReview(@PathVariable("reviewId")Long reviewId,
						@Valid @ModelAttribute("review") Review reviewForm, 
						BindingResult bindingResult, 
						@RequestParam(required = false) String source,
						Model model,RedirectAttributes redirectAttributes) {
		Book book = this.reviewService.findById(reviewId).getBook();
		reviewForm.setBook(book);
		if(bindingResult.hasErrors()) {
			model.addAttribute("review", reviewForm);
			model.addAttribute("genericError", "Something went wrong. Please try again.");
			return "user/updatereview";
		}
		Review review = this.reviewService.findById(reviewId);
		review.setText(reviewForm.getText());
		review.setVote(reviewForm.getVote());
		review.setTitle(reviewForm.getTitle());
		this.reviewService.save(review);
		if(source!=null) {
			redirectAttributes.addFlashAttribute("successSave", "Your review has been saved successfully!");
			return "redirect:/profile";
		}
		redirectAttributes.addFlashAttribute("successSave", "Your review has been saved successfully!");
		return "redirect:/anyone/book/"+ book.getId();
	}
	
	@GetMapping("anyone/reviews")
	public String reviews(Model model) {
		List<Review> reviews = (List<Review>) reviewService.findAll();

		Collections.sort(reviews, new Comparator<Review>() {
		    @Override
		    public int compare(Review r1, Review r2) {
		        // Ordina dalla data più recente alla più vecchia
		        return r2.getDate().compareTo(r1.getDate());
		    }
		});

	    model.addAttribute("allReviews", reviews);
		return "anyone/reviews";
	}
	
	@GetMapping("admin/manageReviews")
	public String manageReviews(Model model) {
		model.addAttribute("allReviews", this.reviewService.findAllByOrderByDateDesc());
		return "admin/manageReviews";
	}
	
	@GetMapping("/admin/review/delete/{reviewId}")
	public String deleteAdminReview(@PathVariable("reviewId")Long reviewId, Model model, RedirectAttributes redirectAttributes) {
		Review review = this.reviewService.findById(reviewId);
		User user = review.getUser();
		user.getReviews().remove(review);
		this.reviewService.deleteById(reviewId);
		redirectAttributes.addFlashAttribute("successDelete", "Your review has been deleted successfully!");
		return "redirect:/admin/manageReviews";	
		}
}
