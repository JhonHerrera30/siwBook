package it.uniroma3.siw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;


@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public Iterable<Author> findAll(){
		return this.authorRepository.findAll();
	}

	public void save(Author author) {
		this.authorRepository.save(author);		
	}

	public Author findById(Long id) {
		return this.authorRepository.findById(id).get();
	}

	public void delete(Long id) {
		this.authorRepository.deleteById(id);
	}

	public List<Author> findByNameAndSurnameAndBornYearAndNationality(String name, String surname, LocalDate bornYear,
			String nationality) {
		return authorRepository.findByNameAndSurnameAndBornYearAndNationality(name, surname, bornYear, nationality);
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public Set<Author> findByNameStartingWithIgnoreCase(String name) { 
		return authorRepository.findByNameStartingWithIgnoreCase(name);
	}

	public Iterable<Author> findAllByIds(List<Long> authorIds) {
		return this.authorRepository.findAllById(authorIds);
	}

	public Set<Author> findBySurnameStartingWithIgnoreCase(String name) {
		return authorRepository.findBySurnameStartingWithIgnoreCase(name);
	}
	
	public Set<Author> findAllAuthors(){
		return this.authorRepository.findAll();
	}
	
	

	
}
