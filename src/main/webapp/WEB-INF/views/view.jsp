<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Page</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/postView.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="main-banner">
    <%-- header --%>
    <div class="header-overlay">
        <jsp:include page="/WEB-INF/views/inc/header.jsp"/>
    </div>
    <%-- 부제목, 제목 --%>
    <div class="text-overlay">
        <p class="subtitle">
            <c:choose>
                <c:when test="${post.status == 'REQUESTING_TRANSFER'}">양도 구해요</c:when>
                <c:when test="${post.status == 'TRANSFERRING'}">양도 해요</c:when>
                <c:when test="${post.status == 'COMPLETED'}">양도 완료</c:when>
            </c:choose>
        </p>
        <h1 class="title">${post.title}</h1>
    </div>
</div>

<%-- 컨텐츠 --%>
<div class="container mt-5">
    <!-- Address -->
    <div class="mb-4">
        <h5 class="fw-bold">Address</h5>
        <p>${post.address}</p>
    </div>

    <div class="mb-4">
        <h5 class="fw-bold">Contents</h5>
        <p>${post.contents}</p>
    </div>

    <%--  카카오 url  --%>
    <div class="mb-4">
        <h5 class="fw-bold">Kakaotalk</h5>
        <p>
            <a href="${post.kakaoUrl}" target="_blank">${post.kakaoUrl}</a>
        </p>
    </div>

    <%-- 이미지 --%>
    <c:if test="${not empty post.fileUrl}">
        <div class="mb-4">
            <c:forEach var="url" items="${fn:split(post.fileUrl, ',')}">
                <img src="${url}" alt="Room Image" class="img-fluid rounded mb-2">
            </c:forEach>
        </div>
    </c:if>

    <%--  버튼  --%>
    <div class="d-flex justify-content-between mt-4">
        <button type="button" class="btn btn-outline-primary c-fc-ppl" onclick="window.history.back();">Back</button>
    </div>
</div>

<!-- 푸터 -->
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
</body>
</html>