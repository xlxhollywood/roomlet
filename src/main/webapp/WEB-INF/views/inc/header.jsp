<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<div class="container-fluid">
    <header class="d-flex align-items-center justify-content-between py-3 mb-4">
        <div class="logo-cont">
            <a href="${pageContext.request.contextPath}/posts/list/all" class="d-inline-flex align-items-center text-decoration-none">
                <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="logo" class="logo">
            </a>
        </div>

        <ul class="nav">
            <li><a href="${pageContext.request.contextPath}/posts/list" class="nav-link">양도구해요</a></li>
            <li><a href="${pageContext.request.contextPath}/posts/transferring" class="nav-link">양도해요</a></li>
            <li><a href="${pageContext.request.contextPath}/posts/create" class="nav-link">글쓰기</a></li>
        </ul>

        <div>
            <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                    <button type="button" class="btn btn-outline-primary" onclick="logout()">Logout</button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-outline-primary" onclick="location.href='${pageContext.request.contextPath}/login'">Login</button>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
</div>

<script>
    async function logout() {
        try {
            const response = await fetch('${pageContext.request.contextPath}/logout', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                // 로그아웃 성공 시 메인 페이지로 이동.
                window.location.href = '${pageContext.request.contextPath}/';
            } else {
                // 실패
                alert("로그아웃 실패.");
            }
        } catch (error) {
            console.error("Error during logout:", error);
            alert("로그아웃 중에 에러가 발생했습니다.");
        }
    }
</script>
</body>
</html>
