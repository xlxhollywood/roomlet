<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
    <script>
        async function handleLogin(event) {
            event.preventDefault();

            // user input
            const memberId = document.getElementById("userId").value;
            const password = document.getElementById("password").value;

            try {
                // ajax request
                const response = await fetch('${pageContext.request.contextPath}/loginOk', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ memberId, password })
                });

                // 상태 코드
                if (response.status === 200) {
                    // 로그인 성공(200)
                    alert("Login successful");
                    window.location.href = '${pageContext.request.contextPath}/posts/list/all'; // 성공 시 리스트 페이지로 이동
                } else if (response.status === 500) {
                    // 로그인 실패(500)
                    document.getElementById("errorMessageContainer").style.display = "block";
                    document.getElementById("errorMessage").textContent = "Invalid username or password";
                } else {
                    // 기타 상태 코드 처리
                    alert("An unexpected error occurred. Please try again.");
                }
            } catch (error) {
                console.error("Error:", error); // 이외 에러
                alert("An unexpected error occurred. Please check your connection.");
            }
        }
    </script>
</head>
<body>
<div class="cont">
    <div class="image-section">
        <img src="${pageContext.request.contextPath}/resources/img/room_1.jpg" alt="Room Image1">
    </div>
    <div class="form-section">
        <form onsubmit="handleLogin(event)">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo" class="logo">
            <h2>Login</h2>
            <div class="form-group">
                <label for="userId">User ID</label>
                <input type="text" class="form-control" id="userId" name="memberId" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div id="errorMessageContainer" class="alert alert-danger" style="display: none;">
                <span id="errorMessage"></span>
            </div>

            <button type="submit" class="btn btn-primary w-100">Login</button>
            <div class="text-center mt-3">
                Don't have an account? <a href="${pageContext.request.contextPath}/members/register">Sign Up</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>