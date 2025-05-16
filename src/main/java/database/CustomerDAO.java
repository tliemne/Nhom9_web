package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import model.Customer;

public class CustomerDAO implements DAOInterface<Customer>{
//	private ArrayList<Customer> data = new ArrayList<>();
	
	@Override
	public ArrayList<Customer> selectAll() {
		ArrayList<Customer> ketQua = new ArrayList<Customer>();
		try {
			// Bước 1: Tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM customer";
			PreparedStatement st = con.prepareStatement(sql);
			
			// Bước 3: Thực thi một câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			
			// Bước 4: xử lý kết quả 
			while(rs.next()) {
				String customerId = rs.getString("customerid");
				String userName = rs.getString("username");
				String passWord = rs.getString("password");
				String customerName = rs.getString("customername");
				String customerGender = rs.getString("customergender");
				java.sql.Date customerDate = rs.getDate("customerdate");
				String customerAddress = rs.getString("customeraddress");
				String customerMobiphone = rs.getString("customermobiphone");
				String customerEmail = rs.getString("customeremail");
				Customer customer = new Customer(customerId, userName, passWord, customerName, customerGender, customerDate, customerAddress, customerMobiphone, customerEmail);
				ketQua.add(customer);
				
			}
	
			// Bước 5: ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	private java.sql.Date Date(Date customerDate) {
	// TODO Auto-generated method stub
	return null;
}

	@Override
	public Customer selectById(Customer t) {
		Customer ketQua = null;
		try {
			// Bước 1: Tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM brand WHERE customerid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			
			// Bước 3: Thực thi một câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			
			
			// Bước 4: xử lý kết quả 
			while(rs.next()) {
				String customerId = rs.getString("customerid");
				String userName = rs.getString("username");
				String passWord = rs.getString("password");
				String customerName = rs.getString("customername");
				String customerGender = rs.getString("customergender");
				Date customerDate = rs.getDate("customerdate");
				String customerAddress = rs.getString("customeraddress");
				String customerMobiphone = rs.getString("customermobiphone");
				String customerEmail = rs.getString("customeremail");
				Customer customer = new Customer(customerId, userName, passWord, customerName,customerGender, Date(customerDate),customerAddress,customerMobiphone,customerEmail);
				break;
			}
			
			// Bước 5: ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(Customer t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO customer (customerid, , username, password,customername,customergender,customerdate,customeraddress,customermobiphone,customeremail) "+
					" VALUES (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			st.setString(2, t.getUsername());
			st.setString(3, t.getPassword());
			st.setString(4, t.getCustomerName());
			st.setString(5, t.getCustomerGender());
			st.setDate(6, t.getCustomerDate());
			st.setString(7, t.getCustomerAddress());
			st.setString(8, t.getCustomerMobiphone());
			st.setString(9, t.getCustomerEmail());
			
			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<Customer> arr) {
		int count = 0;
		for (Customer customer : arr) {
			count += this.insert(customer);
		}
		return count;
	}

	@Override
	public int delete(Customer t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from customer "+
					 " WHERE customerid=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<Customer> arr) {
		int count = 0;
		for (Customer customer : arr) {
			count += this.delete(customer);
		}
		return count;
	}

	@Override
	public int update(Customer t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE customer "+
					 " SET " +
					 " customerid=?"+
					 ", username=?"+
					 ", password=?"+
					 ", customername=?"+
					 ", customergender=?"+
					 ", customerdate=?"+
					 ", customeraddress=?"+
					 ", customermobiphone=?"+
					 ", customeremail=?"+
					 " WHERE customerid=?";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			st.setString(2, t.getUsername());
			st.setString(3, t.getPassword());
			st.setString(4, t.getCustomerName());
			st.setString(5, t.getCustomerGender());
			st.setDate(6, t.getCustomerDate());
			st.setString(7, t.getCustomerAddress());
			st.setString(8, t.getCustomerMobiphone());
			st.setString(9, t.getCustomerEmail());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();
			
			// Bước 4:
			System.out.println("Bạn đã thực thi: "+ sql);
			System.out.println("Có "+ ketQua+" dòng bị thay đổi!");
			
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public static void main(String[] args) {
		CustomerDAO bd = new CustomerDAO();
		ArrayList<Customer> kq = bd.selectAll();
		for (Customer cs : kq) {
			System.out.println(cs.toString());
		}

		 
	}
}
