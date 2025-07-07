package it.uniroma3.siw.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotNull
	@Past
	private LocalDate bornYear;
	
	@Past
	private LocalDate deathYear;
	
	@NotBlank
	private String nationality;
	
	@Column(length = 1000)
	private String description;
	
	private String imageFileName;
	
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBornYear() {
		return bornYear;
	}

	public void setBornYear(LocalDate bornYear) {
		this.bornYear = bornYear;
	}

	public LocalDate getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(LocalDate deathYear) {
		this.deathYear = deathYear;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
    public String getImagePath() {
        if (this.imageFileName == null)
            return "/images/default.jpg";

        Path staticPath = Paths.get("src/main/resources/static/images/authors", this.imageFileName);
        Path uploadPath = Paths.get("uploads/images/authors", this.imageFileName);

        if (Files.exists(staticPath)) {
            return "/images/authors/" + this.imageFileName;
        } else if (Files.exists(uploadPath)) {
            return "/uploads/images/authors/" + this.imageFileName;
        } else {
            return "/images/default.jpg";
        }
    }

	@Override
	public int hashCode() {
		return Objects.hash(bornYear, id, name, surname);
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(bornYear, other.bornYear) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
}
