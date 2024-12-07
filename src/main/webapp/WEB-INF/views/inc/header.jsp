<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-between py-3 px-4 sticky-top" id="header">
    <%--  로고  --%>
    <div class="col-md-3 d-flex align-items-center">
        <a href="${pageContext.request.contextPath}/posts/list" class="d-inline-flex align-items-center">
            <img src="https://cdn.brandfetch.io/idvPXSyqIV/theme/dark/symbol.svg?c=1dxbfHSJFAPEGdCLU4o5B" alt="Logo"
                 width="45" height="45" class="me-2">
            <span class="h4 mb-0">Rentor.com</span>
        </a>
    </div>

    <%--  navigation 탭  --%>
    <nav class="col-md-6 d-flex justify-content-center">
        <ul class="nav">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/posts/list" class="nav-link">Find a Room</a>
            </li>
            <li class="nav-item"><a href="${pageContext.request.contextPath}/posts/create" class="nav-link">Make a Request</a>
            </li>
        </ul>
    </nav>

    <%--   로그인 버튼  --%>
    <div class="col-md-3 text-end">
        <a href="${pageContext.request.contextPath}/members/login" class="btn btn-login">Login</a>
    </div>
</header>

</body>
</html>
