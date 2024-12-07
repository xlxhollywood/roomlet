<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="body-ov">
<%-- 헤더 위치 --%>
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>

<main>
    <div class="card card-signup">
        <div class="card-body">
            <h4 class="card-title text-center mb-4">SIGN UP</h4>
            <form id="registerForm" action="${pageContext.request.contextPath}/members/register" method="post">

                <%-- 아이디, 닉네임 --%>
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label for="memberId" class="form-label">User ID</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="memberId" name="memberId" required>
                            <button class="btn btn-outline-secondary" type="button" id="checkIdBtn">중복 확인</button>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="nickName" class="form-label">Nickname</label>
                        <input type="text" class="form-control" id="nickName" name="nickName" required>
                    </div>
                </div>

                <%-- 패스워드 --%>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <%--  이메일  --%>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <%-- 주소 --%>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="address" name="address" placeholder="주소를 입력하세요." required>
                    </div>
                </div>

                <%-- 연락처 --%>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="하이픈 '-' 없이 입력해주세요." required>
                </div>

                <%-- sign up 버튼 --%>
                <button type="submit" class="btn btn-primary w-100">Sign Up</button>
            </form>

            <!-- Login Link -->
            <div class="text-center mt-3">
                <p>Already a member? <a href="${pageContext.request.contextPath}/members/login" class="text-primary">Login</a></p>
            </div>
        </div>
    </div>
</main>
</body>
</html>