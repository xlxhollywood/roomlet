<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Login Page</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">

</head>
<body class="body-ov">
<%-- 헤더 위치 --%>
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>

<main>
    <div class="card card-login">
        <div class="card-body">
            <h4 class="card-title">LOGIN</h4>
            <form id="loginForm" action="${pageContext.request.contextPath}/members/loginProcess" method="post">
                <%-- User ID 필드 --%>
                <div class="mb-3">
                    <label for="memberId" class="form-label">User ID</label>
                    <input type="text" class="form-control" id="memberId" name="memberId" placeholder="Enter your User ID" required>
                </div>

                <%-- Password 필드 --%>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your Password" required>
                </div>

                <%-- 로그인 버튼 --%>
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>

            <%-- Sign Up 링크 --%>
            <div class="text-center mt-3">
                <p>Not a member? <a href="${pageContext.request.contextPath}/members/register" class="text-primary">Sign up</a></p>
            </div>
        </div>
    </div>
</main>
</body>
</html>