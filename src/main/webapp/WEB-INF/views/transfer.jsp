<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Page</title>

    <!-- CSS 파일 경로 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
<section class="main-banner">
    <!-- 이미지 경로 -->
    <img src="${pageContext.request.contextPath}/resources/img/room_2.jpg" alt="Room Image" class="background-image">

    <div class="header-overlay">
        <jsp:include page="/WEB-INF/views/inc/header.jsp"/>
    </div>

    <div class="text-overlay">
        <h1 class="banner-title">Transfer Housing List</h1>
        <p class="banner-subtitle">양도 중인 집을 찾으시나요? 여기에서 확인하세요.</p>
    </div>
</section>

<div class="container mt-5">
    <!-- 검색 입력 폼 -->
    <form action="${pageContext.request.contextPath}/posts/transferring" method="get" class="d-flex mb-4 ms-auto" style="max-width: 400px;">
        <input type="text" name="keyword" class="form-control" placeholder="Search by title"
               value="${keyword != null ? keyword : ''}" style="margin-right: 0">
        <button type="submit" class="btn btn-primary search-btn" style="color: white;">Search</button>
    </form>

    <!-- 게시글 테이블 -->
    <table class="table table-hover align-middle">
        <thead class="table-light">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>User</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${posts}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/posts/view?id=${post.id}" class="text-decoration-none" style="color: #4f378b">
                            ${post.title}
                    </a>
                </td>
                <td>${post.memberId}</td>
                <td><fmt:formatDate value="${post.createTime}" pattern="yyyy/MM/dd" /></td>
                <td>
                    <c:choose>
                        <c:when test="${post.status == 'REQUESTING_TRANSFER'}">
                            <span class="badge text-bg-success">양도구해요</span>
                        </c:when>
                        <c:when test="${post.status == 'TRANSFERRING'}">
                            <span class="badge text-bg-primary">양도해요</span>
                        </c:when>
                        <c:when test="${post.status == 'COMPLETED'}">
                            <span class="badge text-bg-secondary">양도완료</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge text-bg-dark">미분류</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/posts/edit?id=${post.id}" class="btn btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd"
                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                        </svg>
                    </a>
                    <a href="${pageContext.request.contextPath}/posts/delete?id=${post.id}" class="btn btn-sm">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-trash3-fill" viewBox="0 0 16 16">
                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                        </svg>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty posts}">
            <tr class="mb-5">
                <td colspan="6" class="text-center">검색 결과가 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<%-- footer --%>
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
</body>
</html>
