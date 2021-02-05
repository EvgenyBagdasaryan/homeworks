package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.domain.Author;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AuthorService authorService;
    private final BookService bookService;
    //private final GenreService genreService;
    //private final CommentService commentService;

    //----------- Authors

    @ShellMethod(value = "Create author in table", key = {"ca", "create author"})
    public void createAuthor(String id, String fullName) {
        authorService.saveAuthor(id, new Author(null, fullName));
    }

    @ShellMethod(value = "Read all authors by book id", key = {"ra", "get authors in book"})
    public List<Author> readAuthors(String id) {
        return authorService.getAuthorsByBookId(id);
    }

    @ShellMethod(value = "Update author in book", key = {"ua", "update author"})
    public void updateAuthor(String id, String fullName) {
        authorService.saveAuthor(id, new Author(null, fullName));
    }

    @ShellMethod(value = "Delete author in book by id", key = {"da", "delete author id"})
    public void deleteAuthorById(String id) throws DocumentNotFoundException {
        authorService.deleteById(id);
    }

    @ShellMethod(value = "Delete all authors", key = {"daa", "delete all authors"})
    public void deleteAllAuthors() throws DocumentNotFoundException {
        authorService.deleteAll();
    }

    //----------- Books

    @ShellMethod(value = "Read all books", key = {"rb", "book table read"})
    public List<Book> readBook() {
        return bookService.getBooks();
    }

    @ShellMethod(value = "Read book by id", key = {"rbi", "Read book by id"})
    public Book readBookById(String id) throws DocumentNotFoundException {
        return bookService.getBookById(id);
    }

    @ShellMethod(value = "Read all Comments by book id", key = {"rcbi", "Read all Comments by book id"})
    public List<Comment> readCommentsByBookId(String id)  throws DocumentNotFoundException{
        return bookService.getCommentsBook(id);
    }

    @ShellMethod(value = "Delete book in table by id", key = {"db", "delete book id"})
    public void deleteBookById(String id) throws DocumentNotFoundException {
        bookService.deleteById(id);
    }

    //----------- Genres
    //---------- Comments
}