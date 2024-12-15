<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
</head>
<body>
<div class="cont">
    <div class="image-section">
        <img src="${pageContext.request.contextPath}/resources/img/room_1.jpg" alt="Room Image1">
    </div>
    <div class="form-section">
        <form action="${pageContext.request.contextPath}/members/register" method="post">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo">
            <h2>Sign Up</h2>
            <div class="form-group row">
                <div class="col-md-6">
                    <label for="memberId">User ID</label>
                    <input type="text" class="form-control" id="memberId" name="memberId" required>
                </div>
                <div class="col-md-6">
                    <label for="nickName">Nickname</label>
                    <input type="text" class="form-control" id="nickName" name="nickName" required>
                </div>
            </div>
            <c:if test="${not empty errorMessage}">
                <div class="mb-2" style="color: red" role="alert">
                        ${errorMessage}
                </div>
            </c:if>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="youremail@example.com" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="e.g. 01012345678" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="e.g. 경북 포항시 북구 양덕동 123-456" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Sign Up</button>
            <div class="text-center mt-3">
                Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>