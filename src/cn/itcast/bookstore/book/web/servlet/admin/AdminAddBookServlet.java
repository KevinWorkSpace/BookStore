package cn.itcast.bookstore.book.web.servlet.admin;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAddBookServlet extends HttpServlet {

    private BookService service = new BookService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        try {
            List<FileItem> fileItemList = sfu.parseRequest(request);
            Map<String, String> map = new HashMap<String, String>();
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) {
                  map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                }
            }
            Book book = CommonUtils.toBean(map, Book.class);
            book.setBid(CommonUtils.uuid());
            Category category = CommonUtils.toBean(map, Category.class);
            book.setCategory(category);
            String savepath = this.getServletContext().getRealPath("/book_img");
            String filename = CommonUtils.uuid() + "_" + fileItemList.get(1).getName();
            File destFile = new File(savepath, filename);
            fileItemList.get(1).write(destFile);

            book.setImage("book_img/" + filename);
            service.add(book);
            request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
