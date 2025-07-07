package it.uniroma3.siw.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    private String description;

    @NotNull
    @Max(2025)
    private Integer year;

    private String CoverImageFileName;
    
    @ElementCollection
    private List<String> imageFileNames = new ArrayList<>();

    @ManyToMany
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;
    
    @Transient
    private Double averageVote;

    /* ======= GETTERS & SETTERS ======= */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageFileName() {
		return CoverImageFileName;
	}

	public void setCoverImageFileName(String coverImageFileName) {
		CoverImageFileName = coverImageFileName;
	}

	public List<String> getImageFileNames() {
		return imageFileNames;
	}

	public void setImageFileNames(List<String> imageFileNames) {
		this.imageFileNames = imageFileNames;
	}

	public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Returns the full path (URL) to access the image for this book,
     * checking if it exists in static resources or in the uploads folder.
     */
    @Transient
    public String getImagePath(String imageFileName) {
        if (imageFileName == null)
            return "/images/default.jpg";

        Path staticPath = Paths.get("src/main/resources/static/images/books", imageFileName);
        Path uploadPath = Paths.get("uploads/images/books", imageFileName);

        if (Files.exists(staticPath)) {
            return "/images/books/" + imageFileName;
        } else if (Files.exists(uploadPath)) {
            return "/uploads/images/books/" + imageFileName;
        } else {
            return "/images/default.jpg";
        }
    }

    public Double getAverageVote() {
		return averageVote;
	}

	public void setAverageVote(Double averageVote) {
		this.averageVote = averageVote;
	}

    /* ======= EQUALS & HASHCODE ======= */

	@Override
    public int hashCode() {
        return Objects.hash(id, title, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return Objects.equals(id, other.id)
            && Objects.equals(title, other.title)
            && Objects.equals(year, other.year);
    }
}
