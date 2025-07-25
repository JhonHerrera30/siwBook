package it.uniroma3.siw.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;

public interface ReviewRepository extends CrudRepository<Review,Long>{

	Review getByBookAndUser(Book book, User user);

	List<Review> findByBook(Book book);

	List<Review> findAllByOrderByDateDesc();

}
