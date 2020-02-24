package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher1 = new Publisher("Izdavac", "Ulica 13", "Seattle", "WA", "71075");
        Publisher publisher2 = new Publisher("Bantam", "W81 Broadway", "San Diego", "CA", "92108");

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        Author author1 = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "78543519175");
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);
        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        Author author2 = new Author("Rod", "Johnson");
        Book book2 = new Book("J2EE", "78573527575");
        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);
        book2.setPublisher(publisher1);
        publisher1.getBooks().add(book2);

        authorRepository.save(author2);
        bookRepository.save(book2);

        System.out.println("Started in bootstrap");
        System.out.println("Books added: " + bookRepository.count());
        System.out.println("Authors added: " + authorRepository.count());
        System.out.println("Publishers added: " + publisherRepository.count());
        System.out.println("Publisher1 no of books: " + publisher1.getBooks().size());
        System.out.println("Publisher2 no of books: " + publisher2.getBooks().size());
    }
}
