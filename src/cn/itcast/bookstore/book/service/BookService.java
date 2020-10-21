package cn.itcast.bookstore.book.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;

import java.util.List;

public class BookService {

    private BookDao dao = new BookDao();

    public List<Book> findAll() {
        return dao.findAll();
    }

    public List<Book> findByCategory(String cid) {
        return dao.findByCategory(cid);
    }

    public Book load(String bid) {
        return dao.load(bid);
    }
}
