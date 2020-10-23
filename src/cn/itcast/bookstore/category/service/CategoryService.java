package cn.itcast.bookstore.category.service;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;

import java.util.List;

public class CategoryService {

    private CategoryDao dao = new CategoryDao();

    private BookDao bookDao = new BookDao();

    public List<Category> findAll() {
        return dao.findAll();
    }

    public void add(Category category) {
        dao.add(category);
    }

    public void del(Category category) throws DelectException {
        if (bookDao.findByCategory(category.getCid()).size() != 0) {
            throw new DelectException("有该类别的图书存在，无法删除");
        }
        else {
            dao.delete(category.getCid());
        }
    }

    public Category findById(String cid) {
        return dao.findById(cid);
    }

    public void edit(Category category) {
        dao.edit(category);
    }
}
