package com.api.book.bootrestbook.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;   

    // get all bopks handler
    @GetMapping("/books") // @RequestMapping(value = "/books", method = RequestMethod.GET)

    public ResponseEntity<List<Book>> getBooks() {

        List<Book> list = this.bookService.getAllBooks();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        // Book book = new Book();
        // book.setId(12345);
        // book.setTitle("java ");
        // book.setAuthor("xyz");
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // get single book handler
    @SuppressWarnings("null")
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
       Book book= bookService.getBookById(id);
       if(book==null)
       {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
        return ResponseEntity.of(Optional.of(book));

    }

    // new book handler
    @SuppressWarnings("null")
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;

       try {
         b=this.bookService.addBook(book);
        System.out.println(book);
        return ResponseEntity.of(Optional.of(b));
       } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
       }

    }

    // delete book handler
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookid") int bookid) {
        try {
            this.bookService.deleteBook(bookid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            
        }
    }

    //update book handler
    @PutMapping("/books/{bookid}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookid") int bookid)
    {
        try {
            this.bookService.updateBook(book,bookid);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
