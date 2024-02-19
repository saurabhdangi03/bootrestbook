package com.api.book.bootrestbook.services;

// import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    // private static List<Book> list=new ArrayList<>();

    // static{
    //     list.add(new Book(12,"Java","xyz"));
    //     list.add(new Book(13,"Spring","xyssz"));
    //     list.add(new Book(14,"Python","xyeez"));
    // }
        
    //get all books
    public List<Book> getAllBooks()
    {
        List<Book> list = (List<Book>)this.bookRepository.findAll();
        return list;

    }

    //get single book by id
    public Book getBookById(int id)
    {
      Book book= null;
      try{
      book=this.bookRepository.findById(id);
      
    //   book=list.stream().filter(e->e.getId()==id).findFirst().get();
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return book;

    }
//adding a book
    public Book addBook(Book b)
    {
       Book result = bookRepository.save(b);
        return result;
        
    }

    //delete a book 
    public void deleteBook(int bid)
    {
        bookRepository.deleteById(bid);
    //   list = list.stream().filter(book -> book.getId()!=bid).collect(Collectors.toList());
    // {
    //         if(book.getId()!=bid)
    //         {
    //             return true;
    //         }
    //         else  {
    //             return false;
    //         }
        // }
         
    }

    //update a book
    public void updateBook(Book book, int bookid) 
    {

    //   list = list.stream().map(b -> {
    //         if(b.getId()==bookid)
    //         {
    //             b.setTitle(book.getTitle());
    //             b.setAuthor(book.getAuthor());
    //         }
    //          return b;
    //     }).collect(Collectors.toList()); 

    book.setId(bookid);
    bookRepository.save(book);
    }
     
}
