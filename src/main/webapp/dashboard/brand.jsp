<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Brand - NHOM 9</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/assets/adimgs/favicon.png" rel="icon">
  <link href="${pageContext.request.contextPath}/assets/adimgs/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/adcss/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/adcss/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${pageContext.request.contextPath}/assets/adcss/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Updated: Apr 20 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <jsp:include page="parts/header.jsp" />

  <!-- ======= Sidebar ======= -->
  <jsp:include page="parts/sidebar.jsp" />

  <!-- ======= Nội dung chính của trang ======= -->
  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Quản lý nhà cung cấp</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
          <li class="breadcrumb-item active">Quản lý nhà cung cấp</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Danh sách nhà cung cấp</h5>
              
              <div class="text-end mb-3">
              		<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addBrandModal">
              			<i class="ri-add-circle-fill"> Thêm mới</i>
              		</button>
              </div>
              
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th> Mã nhà cung cấp</th>
                    <th>Tên NCC</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Thực hiện</th>
                    <!-- <th data-type="date" data-format="YYYY/DD/MM">Start Date</th> -->
                  </tr>
                </thead>
                <tbody>
			        <c:forEach var="brand" items="${listBrand}">
				        <tr>
				            <td>${brand.brandId}</td>
				            <td>${brand.brandName}</td>
				            <td>${brand.brandAddress}</td>
				            <td>${brand.brandMobiphone}</td>
				            <td>
				            	<button type="button" class="btn btn-primary"><i class=" ri-eye-fill"></i></button>
					        	<button type="button" class="btn btn-secondary"><i class=" ri-pencil-fill"></i></button>
					        	<button type="button" class="btn btn-danger"><i class=" ri-delete-bin-5-fill"></i></button>
					        </td>
				        </tr>
				    </c:forEach>
                </tbody>
              </table>
              <!-- End Table with stripped rows -->
				
				<!-- Form Thêm Mới Nhà Cung Cấp  -->
				
				<div class="modal fade" id="addBrandModal" tabindex="-1"
					aria-labelledby="addBrandModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-lg">
						<!-- modal-lg cho khung rộng hơn -->
						<div class="modal-content border-0 shadow-lg rounded-4">
							<form action="addBrand" method="post">
								<div class="modal-header bg-primary text-white rounded-top-4">
									<h5 class="modal-title" id="addBrandModalLabel">
										<i class="ri-building-4-line me-2"></i>Thêm nhà cung cấp
										mới
									</h5>
									<button type="button" class="btn-close btn-close-white"
										data-bs-dismiss="modal" aria-label="Đóng"></button>
								</div>
								<div class="modal-body px-4 pt-4">
									<div class="row g-3">
										<div class="col-md-12">
											<label for="brandName" class="form-label">Tên nhà
												cung cấp</label>
											<div class="input-group">
												<span class="input-group-text"><i
													class="ri-user-line"></i></span> <input type="text"
													class="form-control" id="brandName" name="brandName"
													placeholder="Nhập tên nhà cung cấp" required>
											</div>
										</div>
										<div class="col-md-12">
											<label for="brandAddress" class="form-label">Địa
												chỉ</label>
											<div class="input-group">
												<span class="input-group-text"><i
													class="ri-map-pin-line"></i></span> <input type="text"
													class="form-control" id="brandAddress"
													name="brandAddress" placeholder="Nhập địa chỉ" required>
											</div>
										</div>
										<div class="col-md-12">
											<label for="brandMobiphone" class="form-label">Số
												điện thoại</label>
											<div class="input-group">
												<span class="input-group-text"><i
													class="ri-phone-line"></i></span> <input type="text"
													class="form-control" id="brandMobiphone"
													name="brandMobiphone" placeholder="Nhập số điện thoại"
													required>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer px-4 pb-4">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">
										<i class="ri-close-line"></i> Hủy
									</button>
									<button type="submit" class="btn btn-success">
										<i class="ri-check-line"></i> Lưu
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
	
	
	
				<!-- End Form Thêm mới  -->
            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="${pageContext.request.contextPath}/assets/adjs/apexcharts.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/adjs/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/adjs/chart.umd.js"></script>
  <script src="${pageContext.request.contextPath}/assets/adjs/echarts.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/quill/quill.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="${pageContext.request.contextPath}/assets/adjs/main.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Remix Icon -->
<link href="https://cdn.jsdelivr.net/npm/remixicon/fonts/remixicon.css" rel="stylesheet">

</body>

</html>