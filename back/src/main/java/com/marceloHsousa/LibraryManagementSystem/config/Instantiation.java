package com.marceloHsousa.LibraryManagementSystem.config;

import com.marceloHsousa.LibraryManagementSystem.entities.author.Author;
import com.marceloHsousa.LibraryManagementSystem.entities.book.Book;
import com.marceloHsousa.LibraryManagementSystem.entities.category.Category;
import com.marceloHsousa.LibraryManagementSystem.entities.loan.Loan;
import com.marceloHsousa.LibraryManagementSystem.entities.user.User;
import com.marceloHsousa.LibraryManagementSystem.repositories.LoanRepository;
import com.marceloHsousa.LibraryManagementSystem.repositories.UserRepository;
import com.marceloHsousa.LibraryManagementSystem.services.BookService;
import com.marceloHsousa.LibraryManagementSystem.services.LoanService;
import com.marceloHsousa.LibraryManagementSystem.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    private UserService userService;
    private LoanService loanService;
    private BookService bookService;
    private PasswordEncoder passwordEncoder;

    public Instantiation(UserService userService, LoanService loanService, BookService bookService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.loanService = loanService;
        this.bookService = bookService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = new ArrayList<>();

        users.add(new User(null, "Gabriel Meireles", "gabriel@gmail.com", passwordEncoder.encode("gabe123"), "ROLE_ADMIN", null));
        users.add(new User(null, "Ana Silva", "ana@gmail.com", passwordEncoder.encode("ana456"), "ROLE_USER", null));
        users.add(new User(null, "João Santos", "joao@gmail.com", passwordEncoder.encode("joao789"), "ROLE_USER", null));
        users.add(new User(null,"Maria Oliveira", "maria@gmail.com", passwordEncoder.encode("maria321"), "ROLE_ADMIN", null));

        userService.saveAll(users);
        User userTest=userService.findByEmail("gabriel@gmail.com");

        Author author=new Author(null, "Eliabe Vigelis", new Date(), "Russo");

        List<Category> categories=new ArrayList<>();

        categories.add(new Category(null, "Ação", "Livros de Ação", null));
        categories.add(new Category(null, "Drama", "Livros de Drama", null));

        List<Book> books=new ArrayList<>();

        books.add(new Book(null, "As Cronicas de Marcelinho", "933-59-89965-02-3", true, author, categories));
        books.add(new Book(null, "As Aventura de Leo Bolsonaro", "412-29-85675-22-1", true, author, categories));

        userTest.setLoans(loanService.findLoansByUser(userTest));
        userTest.addLoan(new Loan(null, new Date(), new Date(), books.get(0)));
        userTest.addLoan(new Loan(null, new Date(), new Date(), books.get(1)));

        userService.save(userTest);
    }
}
