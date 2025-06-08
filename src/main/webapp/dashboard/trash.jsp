<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thùng Rác Tài Khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/remixicon/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">🗑️ Thùng rác - Tài khoản đã xóa</h2>

        <a href="${pageContext.request.contextPath}/customer" class="btn btn-secondary mb-3">
            <i class="ri-arrow-left-line"></i> Quay lại danh sách
        </a>

        <table class="table table-bordered table-striped">
            <thead class="table-dark text-center">
                <tr>
                    <th>ID</th>
                    <th>Tên đăng nhập</th>
                    <th>Họ tên</th>
                    <th>Giới tính</th>
                    <th>Địa chỉ</th>
                    <th>Quyền Admin</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${listCustomer}">
                    <tr class="text-center">
                        <td>${customer.customerId}</td>
                        <td>${customer.username}</td>
                        <td>${customer.customerName}</td>
                        <td>${customer.customerGender}</td>
                        <td>${customer.customerAddress}</td>
                        <td>${customer.isAdmin ? "✔" : "✖"}</td>
                        <td>
                            <!-- Khôi phục -->
                            <form action="CustomerController" method="post" style="display:inline-block;">
                                <input type="hidden" name="action" value="restore" />
                                <input type="hidden" name="customerId" value="${customer.customerId}" />
                                <button type="submit" class="btn btn-success btn-sm" title="Khôi phục">
                                    <i class="ri-arrow-go-back-line"></i>
                                </button>
                            </form>

                            <!-- Xóa vĩnh viễn -->
                            <form action="CustomerController" method="post" style="display:inline-block;"
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản này không?');">
                                <input type="hidden" name="action" value="deletePermanent" />
                                <input type="hidden" name="customerId" value="${customer.customerId}" />
                                <button type="submit" class="btn btn-danger btn-sm" title="Xóa vĩnh viễn">
                                    <i class="ri-delete-bin-2-fill"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
