<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Footer</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
<footer class="footer bg-purple-600 text-white">
  <div class="footer-container">
    <%--  로고  --%>
    <div class="footer-logo">
      <a href="${pageContext.request.contextPath}/posts/list" class="d-inline-flex align-items-center text-decoration-none">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo" class="logo">
      </a>
    </div>
    <div class="footer-bottom">
      <p class="footer-text">© 2024 Copyright:</p>
      <p class="footer-text">Web Service Development Project</p>
    </div>
  </div>
</footer>
</body>
</html>