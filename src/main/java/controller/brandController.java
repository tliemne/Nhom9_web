package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BrandDAO;
import model.Brand;

/**
 * Servlet implementation class brandController
 */
@WebServlet("/quanly-ncc")
public class brandController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public brandController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BrandDAO brandDao = new BrandDAO();
		ArrayList<Brand> listBrand = brandDao.selectAll();
		
		// 1. Gửi request lên server:
		request.setAttribute("listBrand", listBrand);
		
		// 2. Chuyển dữ liệu từ server lên trang giao diện brand.jsp:
		RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard/brand.jsp");
		dispatcher.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
