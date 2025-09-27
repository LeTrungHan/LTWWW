<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hệ thống đăng ký người dùng</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
      }
      .container {
        max-width: 600px;
        margin: 0 auto;
        background: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
      }
      h1 {
        color: #333;
        margin-bottom: 30px;
      }
      .description {
        color: #666;
        font-size: 16px;
        margin-bottom: 40px;
        line-height: 1.6;
      }
      .btn {
        background-color: #007bff;
        color: white;
        padding: 15px 30px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        text-decoration: none;
        display: inline-block;
        margin: 10px;
        transition: background-color 0.3s;
      }
      .btn:hover {
        background-color: #0056b3;
      }
      .btn-secondary {
        background-color: #6c757d;
      }
      .btn-secondary:hover {
        background-color: #545b62;
      }
      .features {
        text-align: left;
        margin: 30px 0;
      }
      .features h3 {
        color: #333;
        margin-bottom: 15px;
      }
      .features ul {
        color: #666;
        padding-left: 20px;
      }
      .features li {
        margin-bottom: 8px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Hệ thống đăng ký người dùng</h1>

      <div class="description">
        Chào mừng bạn đến với hệ thống đăng ký người dùng được xây dựng bằng JSP
        và Servlet theo mô hình MVC.
      </div>

      <div class="features">
        <h3>Tính năng:</h3>
        <ul>
          <li>Đăng ký tài khoản người dùng mới</li>
          <li>Xem danh sách tất cả người dùng</li>
          <li>Xóa người dùng không cần thiết</li>
          <li>Validation dữ liệu đầu vào</li>
          <li>Giao diện thân thiện và responsive</li>
        </ul>
      </div>

      <a href="${pageContext.request.contextPath}/register" class="btn"
        >Đăng ký người dùng mới</a
      >
      <a
        href="${pageContext.request.contextPath}/users"
        class="btn btn-secondary"
        >Xem danh sách người dùng</a
      >
    </div>
  </body>
</html>
