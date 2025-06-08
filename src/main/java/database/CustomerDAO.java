package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import java.util.*;
import java.sql.Date;

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
			String sql = "SELECT * FROM customer WHERE is_deleted = FALSE";
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
				Date customerDate = rs.getDate("customerdate");
				String customerAddress = rs.getString("customeraddress");
				String customerMobiphone = rs.getString("customermobiphone");
				String customerEmail = rs.getString("customeremail");
				String isAdmin = rs.getString("customeradmin");
				boolean isDeleted = rs.getBoolean("is_deleted");
				Customer customer = new Customer(customerId, userName, passWord, customerName, customerGender, customerDate, customerAddress, customerMobiphone, customerEmail,isAdmin,isDeleted);
				ketQua.add(customer);
				
			}
	
			// Bước 5: ngắt kết nối
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	

	@Override
	public Customer selectById(Customer t) {
		Customer ketQua = null;
		try {
			// Bước 1: Tạo kết nối
			Connection con = JDBCUtil.getConnection();
			
			// Bước 2: Tạo ra đối tượng statement
			String sql = "SELECT * FROM customer WHERE customerid=?";
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
				String isAdmin = rs.getString("customeradmin");
				boolean isDeleted = rs.getBoolean("is_deleted");
				Customer customer = new Customer(customerId, userName, passWord, customerName,customerGender, customerDate,customerAddress,customerMobiphone,customerEmail,isAdmin,isDeleted);
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
			String rawPassword = t.getPassword();
	        if (rawPassword != null && !rawPassword.startsWith("$2a$")) {
	            String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
	            t.setPassword(hashedPassword);
	        }
			
			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO customer (customerid, username, password,customername,customergender,customerdate,customeraddress,customermobiphone,customeremail,customeradmin,is_deleted) "+
					" VALUES (?,?,?,?,?,?,?,?,?,?,FALSE)";
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getCustomerId());
			st.setString(2, t.getUsername());
			st.setString(3,t.getPassword());
			st.setString(4, t.getCustomerName());
			st.setString(5, t.getCustomerGender());
			st.setDate(6, t.getCustomerDate());
			st.setString(7, t.getCustomerAddress());
			st.setString(8, t.getCustomerMobiphone());
			st.setString(9, t.getCustomerEmail());
			st.setString(10,t.getIsAdmin());
			
			
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
	        Connection con = JDBCUtil.getConnection();
	        String rawPassword = t.getPassword();
	        if (rawPassword != null && !rawPassword.startsWith("$2a$")) {
	            String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
	            t.setPassword(hashedPassword);
	        }
	        String sql = "UPDATE customer " +
	                     " SET " +
	                     " username=?," +
	                     " password=?," +
	                     " customername=?," +
	                     " customergender=?," +
	                     " customerdate=?," +
	                     " customeraddress=?," +
	                     " customermobiphone=?," +
	                     " customeremail=?," +
	                     " customeradmin=?" +
	                    
	                     " WHERE customerid=?";

	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, t.getUsername());
	       st.setString(2,t.getPassword());
	        st.setString(3, t.getCustomerName());
	        st.setString(4, t.getCustomerGender());
	        st.setDate(5, t.getCustomerDate());
	        st.setString(6, t.getCustomerAddress());
	        st.setString(7, t.getCustomerMobiphone());
	        st.setString(8, t.getCustomerEmail());
	        st.setString(9,t.getIsAdmin());
	       
	        st.setString(10, t.getCustomerId()); // điều kiện WHERE
	        System.out.println(sql);
	        ketQua = st.executeUpdate();

	        System.out.println("Bạn đã thực thi: " + sql);
	        System.out.println("Có " + ketQua + " dòng bị thay đổi!");

	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}

	public String generateNextCustomerId() {
	    String nextId = "KH001"; // giá trị mặc định nếu bảng rỗng

	    try {
	        Connection con = JDBCUtil.getConnection();

	        String sql = "SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String lastId = rs.getString("customerId"); // VD: KH050
	            // Cắt bỏ 2 ký tự đầu ("KH"), giữ lại phần số
	            int nextNum = Integer.parseInt(lastId.substring(2)) + 1;
	            nextId = String.format("KH%03d", nextNum); // Format lại KH### 
	        }

	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Lỗi khi sinh tài khoản mới!");
	    }

	    return nextId;
	}
	

	// Hàm mã hóa lại mật khẩu cho tất cả khách hàng nếu chưa được mã hóa
	public void encryptExistingPasswords() {
	    ArrayList<Customer> customers = this.selectAll();
	    for (Customer customer : customers) {
	        String currentPassword = customer.getPassword();
	        System.out.println("Tài khoản: " + customer.getUsername());
	        System.out.println("Mật khẩu hiện tại: " + currentPassword);

	        if (currentPassword != null && !currentPassword.startsWith("$2a$")) {
	            String hashedPassword = BCrypt.hashpw(currentPassword, BCrypt.gensalt());
	            System.out.println("Mật khẩu sau mã hóa: " + hashedPassword);

	            customer.setPassword(hashedPassword);
	            int kq = this.update(customer);  // update không mã hóa nữa
	            System.out.println("Kết quả update: " + kq);
	        } else {
	            System.out.println("Bỏ qua vì mật khẩu đã mã hóa hoặc null.");
	        }
	    }
	}
	public ArrayList<Customer> selectDeleted() {
	    ArrayList<Customer> ketQua = new ArrayList<>();
	    try {
	        Connection con = JDBCUtil.getConnection();

	        String sql = "SELECT * FROM customer WHERE is_deleted = TRUE";
	        PreparedStatement st = con.prepareStatement(sql);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            String customerId = rs.getString("customerid");
	            String userName = rs.getString("username");
	            String passWord = rs.getString("password");
	            String customerName = rs.getString("customername");
	            String customerGender = rs.getString("customergender");
	            Date customerDate = rs.getDate("customerdate");
	            String customerAddress = rs.getString("customeraddress");
	            String customerMobiphone = rs.getString("customermobiphone");
	            String customerEmail = rs.getString("customeremail");
	            String isAdmin = rs.getString("customeradmin");
	            boolean isDeleted = rs.getBoolean("is_deleted"); // <-- thêm dòng này

	            Customer customer = new Customer(customerId, userName, passWord, customerName, customerGender, customerDate, customerAddress, customerMobiphone, customerEmail, isAdmin,isDeleted);
	            

	            ketQua.add(customer);
	        }

	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}
	public int softDeleteById(String customerId) {
	    int ketQua = 0;
	    String sql = "UPDATE customer SET is_deleted = TRUE WHERE customerid = ?";

	    try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	        st.setString(1, customerId);
	        ketQua = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}

	// Khôi phục khách hàng bị xóa bằng ID
	public int restoreById(String customerId) {
	    int ketQua = 0;
	    String sql = "UPDATE customer SET is_deleted = FALSE WHERE customerid = ?";

	    try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	        st.setString(1, customerId);
	        ketQua = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}

	// Xóa vĩnh viễn khách hàng bằng ID
	public int deleteById(String customerId) {
	    int ketQua = 0;
	    String sql = "DELETE FROM customer WHERE customerid = ? AND is_deleted = TRUE";

	    try (Connection con = JDBCUtil.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	        st.setString(1, customerId);
	        ketQua = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ketQua;
	}
	/*
	 * public static void main(String[] args) { CustomerDAO bd = new CustomerDAO();
	 * 
	 * // Mã hóa lại toàn bộ mật khẩu chưa được mã hóa
	 * bd.encryptExistingPasswords();
	 * 
	 * // Kiểm tra lại danh sách khách hàng ArrayList<Customer> kq = bd.selectAll();
	 * for (Customer cs : kq) { System.out.println(cs.toString()); } }
	 */
	}
