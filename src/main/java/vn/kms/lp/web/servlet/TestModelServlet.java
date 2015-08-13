package vn.kms.lp.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.kms.lp.web.dao.TestDAO;
import vn.kms.lp.web.dao.impl.TestDAOMemoryImpl;
import vn.kms.lp.web.model.TestModel;

/**
 * @author thanhtran
 *
 */
@WebServlet("/testmodel")
public class TestModelServlet extends HttpServlet {

    private static final long serialVersionUID = 2368611017169777703L;

    private TestDAO testDAO;

    @Override
    public void init() throws ServletException {
        testDAO = TestDAOMemoryImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("testModelId").toString());
            TestModel model = testDAO.getById(id);
            request.setAttribute("model", model);
        } catch (Exception ignore) {
            // Ignore all exception
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/test/view.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            TestModel model = new TestModel();
            model.setAttribute1(req.getParameter("attribute1"));
            model.setAttribute2(Long.parseLong(req.getParameter("attribute2")));
            testDAO.save(model);
            out.println("Success");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
