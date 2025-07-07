package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.BookValidator;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AuthorService;
import it.uniroma3.siw.service.BookService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;
import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired 
	private AuthorService authorService;

	@Autowired
	private BookValidator bookValidator;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CredentialsService credentialsService;

	@GetMapping(value = "/anyone/books")
	public String listBooks(@RequestParam(value = "title", required = false) String title, Model model) {
	    List<Book> books;

	    if (title != null && !title.isEmpty()) {
	        books = bookService.findByTitleStartingWithIgnoreCase(title);
	    } else {
	        books = (List<Book>) bookService.findAll();
	    }
		for(Book book: books) {
			Double avgVote = reviewService.getAverageVoteForBook(book);
			if(avgVote!=null && avgVote > 0) {
				book.setAverageVote(avgVote);
			}
			else {
				book.setAverageVote(0.0);
			}
		}
		model.addAttribute("books", books);
		return "anyone/books";
	}

	@GetMapping(value = "/anyone/book/{id}")
	public String book(@PathVariable("id") Long bookId, Model model) {
		Book book = this.bookService.findById(bookId);
		Double avgVote = reviewService.getAverageVoteForBook(book);
		if(avgVote!=null && avgVote >0) {
			book.setAverageVote(avgVote);
		}
		else {
			book.setAverageVote(0.0);
		}
		model.addAttribute("book", book);
		List<Review> otherReviews = reviewService.findByBook(book);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Se l'utente NON è autenticato
		if (authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()) {
			// Mostra solo le recensioni pubbliche
			model.addAttribute("otherReviews", otherReviews);
			return "anyone/book";
		}

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		// Review scritta da questo utente (se esiste)
		Review userReview = reviewService.getByBookAndUser(book, user);
		if(userReview!=null && user.getReviews().contains(userReview)) {
			model.addAttribute("userReview",userReview );
			// Altre recensioni
			otherReviews.remove(userReview);
		}
		model.addAttribute("otherReviews", otherReviews);
		return "anyone/book";
	}

	@GetMapping(value = "admin/manageBooks")
	public String manageBook(@RequestParam(value = "title", required = false) String title, Model model) {
		List<Book> books;

	    if (title != null && !title.isEmpty()) {
	        books = bookService.findByTitleStartingWithIgnoreCase(title);
	    } else {
	        books = (List<Book>) bookService.findAll();
	    }
		model.addAttribute("books", books);
		return "admin/manageBooks";
	}


	@GetMapping(value = "admin/formNewBook")
	public String formNewBook(
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "title", required = false) String title,
	        @RequestParam(value = "description", required = false) String description,
	        @RequestParam(value = "year", required = false) Integer year,
	        @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
	        Model model) {

	    Book book = new Book();
	    book.setTitle(title);
	    book.setDescription(description);
	    book.setYear(year);

	    List<Long> selectedAuthorIds = new ArrayList<>();
	    if (authorIds != null && !authorIds.isEmpty()) {
	        List<Author> selectedAuthors = (List<Author>) authorService.findAllByIds(authorIds);
	        book.setAuthors(selectedAuthors);
	        selectedAuthorIds = authorIds;
	    }

	    model.addAttribute("book", book);

	    Set<Author> authors;
		if (name != null && !name.isEmpty()) {
	        authors = authorService.findByNameStartingWithIgnoreCase(name);
	        authors.addAll(authorService.findBySurnameStartingWithIgnoreCase(name));
	    } else {
	        authors = (Set<Author>) authorService.findAll();
	    }
	    model.addAttribute("authors", authors);
	    if(authorIds!=null)
	    	model.addAttribute("selectedAuthorIds", selectedAuthorIds); 
	    else
	    	model.addAttribute("selectedAuthorIds", new ArrayList<>()); 
	    return "admin/formNewBook";
	}
	
	@PostMapping("admin/book/new")
	public String submitBookForm(@Valid @ModelAttribute("book") Book book,
	                              BindingResult bindingResult,
	                              @RequestParam("image") MultipartFile imageFile,
	                              @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
	                              Model model) {

		// Conversione degli ID in oggetti Author
		if (authorIds != null && !authorIds.isEmpty()) {
	        List<Author> selectedAuthors = new ArrayList<>();
	        for (Long authorId : authorIds) {
	            Author author = authorService.findById(authorId);
	            if (author != null) {
	                selectedAuthors.add(author);
	            }
	        }
	        book.setAuthors(selectedAuthors);
	    } else {
	    	model.addAttribute("noAuthorsError", "Select at least one author");
	    	model.addAttribute("authors", authorService.findAll());
	    	if(authorIds!=null)
		    	model.addAttribute("selectedAuthorIds", authorIds); 
		    else
		    	model.addAttribute("selectedAuthorIds", new ArrayList<>()); 
	    	return "admin/formNewBook";
	    }
		
	    this.bookValidator.validate(book, bindingResult);

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("authors", authorService.findAll());
	        model.addAttribute("selectedAuthorIds", authorIds);
	        return "admin/formNewBook";
	    }
	    // Gestione upload immagine
	    if (!imageFile.isEmpty()) {
	        try {
	        	String fileName = imageFile.getOriginalFilename();

	            // Nuovo path: uploads/images/books
	            Path uploadPath = Paths.get("uploads", "images", "books");

	            // Crea la cartella se non esiste
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            // Salva l'immagine nel percorso completo
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	            // Salva solo il nome del file nel database
	            book.setCoverImageFileName(fileName);

	        } catch (IOException e) {
	            e.printStackTrace(); // Puoi anche loggare o aggiungere errori al model
	        }
	    }

	    this.bookService.save(book);

	    return "redirect:/admin/updateBook/" + book.getId(); // se vuoi tornare alla modifica
	 }


	@GetMapping("/admin/updateBook/{id}")
	public String updateBook(@PathVariable("id") Long bookId, @RequestParam(value = "name", required = false) String name, 
			Model model) {
		Book book = this.bookService.findById(bookId);
		return this.prepareUpdateBookPage(book, model, name);
	}

	@PostMapping("/admin/book/update/{id}")
	public String updateBookInfo(@PathVariable("id") Long id,
			@Valid @ModelAttribute("book") Book bookForm,
			BindingResult bindingResult,
			@RequestParam("image") MultipartFile imageFile,
			@RequestParam(value = "authors", required = false) List<Long> authorIds,
			@RequestParam(value = "name", required = false) String name,
			Model model) {

		Book originalBook = this.bookService.findById(id);

		// Ricostruisci autori
		List<Author> selectedAuthors = new ArrayList<>();
		if (authorIds != null) {
			for (Long authorId : authorIds) {
				Author author = this.authorService.findById(authorId);
				if (author != null) {
					selectedAuthors.add(author);
				}
			}
		}
		bookForm.setCoverImageFileName(originalBook.getCoverImageFileName());
		bookForm.setAuthors(selectedAuthors);

		// Esegui la validazione
		this.bookValidator.validate(bookForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("genericError", "Something went wrong. Please try again.");
			return this.prepareUpdateBookPage(bookForm, model, name);
		}

		// Applica modifiche solo se non ci sono errori
		originalBook.setTitle(bookForm.getTitle());
		originalBook.setYear(bookForm.getYear());
		originalBook.setDescription(bookForm.getDescription());
		originalBook.setAuthors(selectedAuthors);

		// Gestione immagine
		if (!imageFile.isEmpty()) {
			try {
				String fileName = imageFile.getOriginalFilename();
				Path uploadPath = Paths.get("uploads/images/books");
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				originalBook.setCoverImageFileName(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.bookService.save(originalBook);
		model.addAttribute("success", "Changes saved successfully!");
		return this.prepareUpdateBookPage(originalBook, model, name);
	}


	@PostMapping("/admin/book/{bookId}/add-author/{authorId}")
	public String addAuthorToBook(@PathVariable("bookId") Long bookId,
			@PathVariable("authorId") Long authorId,
			@RequestParam(value = "name", required = false) String name,
			Model model) {
		Book book = this.bookService.findById(bookId);
		Author author = this.authorService.findById(authorId);

		if (!book.getAuthors().contains(author)) {
			book.getAuthors().add(author);
		}
		// Validazione custom
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(book, "book");
		this.bookValidator.validate(book, result);

		if (result.hasErrors()) {
			// Ripristina l’autore rimosso
			book.getAuthors().remove(author);
			model.addAttribute("genericError", "Something went wrong. Please try again.");
			model.addAttribute("authorErrorBookDuplicate", "This change would create a duplicate book.");
			return this.prepareUpdateBookPage(book, model, name);
		}
		this.bookService.save(book);
		model.addAttribute("success", "Changes saved successfully!");
		return this.prepareUpdateBookPage(book, model, name);
	}

	@PostMapping("/admin/book/{bookId}/remove-author/{authorId}")
	public String removeAuthorFromBook(@PathVariable("bookId") Long bookId,
			@PathVariable("authorId") Long authorId,
			@RequestParam(value = "name", required = false) String name,
			Model model) {
		Book book = this.bookService.findById(bookId);
		Author author = this.authorService.findById(authorId);

		// Caso base: impedire rimozione se è l'ultimo autore
		if (book.getAuthors().size() == 1) {
			model.addAttribute("noAuthors", "A book must have at least one author.");
			return this.prepareUpdateBookPage(book, model, name);
		}

		// Rimuovi temporaneamente
		book.getAuthors().remove(author);

		// Validazione custom
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(book, "book");
		this.bookValidator.validate(book, result);

		if (result.hasErrors()) {
			// Ripristina l’autore rimosso
			book.getAuthors().add(author);
			model.addAttribute("genericError", "Something went wrong. Please try again.");
			model.addAttribute("authorErrorBookDuplicate", "This change would create a duplicate book.");
			return this.prepareUpdateBookPage(book, model, name);
		}
		// Se tutto ok, salva
		this.bookService.save(book);
		model.addAttribute("success", "Changes saved successfully!");
		return this.prepareUpdateBookPage(book, model, name);
	}


	@GetMapping("/admin/book/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		Book book = this.bookService.findById(id);
		List<Review> allBookReviews = (List<Review>) this.reviewService.findByBook(book);
		this.reviewService.deleteAll(allBookReviews);
		this.bookService.delete(id);
		model.addAttribute("books", this.bookService.findAll());
		model.addAttribute("success", "Book successfully deleted.");
		return "admin/manageBooks";
	}
	
	@PostMapping("/admin/book/{bookId}/remove-image/{imageName:.+}")
	public String removeImageFromBook(@PathVariable Long bookId,
	                                  @PathVariable String imageName,
	                                  @RequestParam(value = "name", required = false) String name,
	                                  Model model) {
	    Book book = bookService.findById(bookId);

	    if (!bookService.removeImage(book, imageName)) {
	        model.addAttribute("genericErrorImg", "Image could not be removed.");
	    } else {
	        model.addAttribute("successDeleteImg", "Image removed successfully.");
	    } 

	   return this.prepareUpdateBookPage(book, model, name);
	}
	
	@PostMapping("/admin/book/{bookId}/add-images")
	public String addExtraImages(@PathVariable Long bookId,
	                             @RequestParam("extraImages") List<MultipartFile> files,
	                             @RequestParam(value = "name", required = false) String name,
	                             Model model) {
	    Book book = bookService.findById(bookId);

	    // Cartella di destinazione
	    Path uploadPath = Paths.get("uploads", "images", "books");

	    try {
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        List<String> newImageNames = new ArrayList<>();

	        for (MultipartFile file : files) {
	            if (!file.isEmpty()) {
	                String fileName = file.getOriginalFilename();
	                Path filePath = uploadPath.resolve(fileName);
	                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	                newImageNames.add(fileName);
	            }
	        }

	        // Aggiorna la lista immagini solo se ci sono immagini nuove
	        if (!newImageNames.isEmpty()) {
	            if (book.getImageFileNames() == null) {
	                book.setImageFileNames(new ArrayList<>());
	            }
	            book.getImageFileNames().addAll(newImageNames);
	            bookService.save(book);
	            model.addAttribute("success", "Images uploaded successfully.");
	        } else {
	            model.addAttribute("genericErrorImg", "No valid image selected.");
	        }

	    } catch (IOException e) {
	        model.addAttribute("genericErrorImg", "Error uploading images.");
	        e.printStackTrace();
	    }

	    return this.prepareUpdateBookPage(book, model, name);
	}


	private String prepareUpdateBookPage(Book book, Model model, String name) {
		model.addAttribute("book", book);
		Set<Author> authorsNotInBook;
		if (name != null && !name.isEmpty()) {
			authorsNotInBook = authorService.findByNameStartingWithIgnoreCase(name);
	        authorsNotInBook.addAll(authorService.findBySurnameStartingWithIgnoreCase(name));
	    } else {
	    	authorsNotInBook = authorService.findAllAuthors();
	    }
		authorsNotInBook.removeAll(book.getAuthors());
		model.addAttribute("authorsNotInBook", authorsNotInBook);
		return "admin/updateBook";
	}
}
