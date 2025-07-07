package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> findByBook (Book book){
		return this.reviewRepository.findByBook(book); 
	}

	public Review getByBookAndUser(Book book, User user) {
		return this.reviewRepository.getByBookAndUser(book, user);
	}

	public void save(Review review) {
		this.reviewRepository.save(review);
	}
	
	public Double getAverageVoteForBook(Book book) {
	    List<Review> reviews = reviewRepository.findByBook(book);
	    if (reviews.isEmpty()) return null;
	    return reviews.stream()
	                  .mapToInt(Review::getVote)
	                  .average()
	                  .orElse(0.0);
	}

	public void deleteById(Long reviewId) {
		this.reviewRepository.deleteById(reviewId);
	}

	public Iterable<Review> findAll() {
		return this.reviewRepository.findAll();
	}

	public void deleteAll(List<Review> allBookReviews) {
		this.reviewRepository.deleteAll(allBookReviews);	
	}

	public Review findById(Long reviewId) {
		return this.reviewRepository.findById(reviewId).get();
	}

	public List<Review> findAllByOrderByDateDesc() {
		return this.reviewRepository.findAllByOrderByDateDesc();
	}

	public long countReviews() {
		return reviewRepository.count();
	}

}
