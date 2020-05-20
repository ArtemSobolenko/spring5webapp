package com.springframework.spring5webapp.bootstrap;

import com.springframework.spring5webapp.model.Author;
import com.springframework.spring5webapp.model.Book;
import com.springframework.spring5webapp.model.Publisher;
import com.springframework.spring5webapp.repositories.AuthorRepository;
import com.springframework.spring5webapp.repositories.BookRepository;
import com.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher publisher1 = new Publisher();
        publisher1.setName("Prentice Hall");
        publisher1.setAddress("street1");
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Addison-Wesley Professional");
        publisher2.setAddress("street2");
        publisherRepository.save(publisher2);

        Author bruce = new Author("Bruce", "Eckel");
        Book tij = new Book("Thinking in Java", "9780131872486", publisher1);
        bruce.getBooks().add(tij);
        tij.getAuthors().add(bruce);

        authorRepository.save(bruce);
        bookRepository.save(tij);

        Author joshua = new Author("Joshua", "Bloch");
        Book ej = new Book("Effective Java", "9780134686042", publisher2);
        joshua.getBooks().add(ej);
        ej.getAuthors().add(joshua);

        authorRepository.save(joshua);
        bookRepository.save(ej);

    }
}
