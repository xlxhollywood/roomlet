<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>View Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post.css">

    <style>

    </style>
</head>
<body>
<%-- header --%>
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>

<div class="view-container mt-5">
    <div class="view-card">
        <div class="view-card-header">
            Post Details
        </div>
        <div class="card-body">
            <%--   제목, 상태   --%>
            <div class="title-status-row">
                <div class="title">양덕동 투룸 양도</div>
                <div class="status green">양도 해요</div>
            </div>

            <%-- 상세 내용 --%>
            <div class="content-group">
                <div class="content-label">Contents</div>
                <div class="content-value">
                    넓고 깨끗한 투룸 양도합니다. 버즈 정류장까지 5분 걸리고 집 주인 분과 연락도 잘 됩니다.
                </div>
            </div>

            <%--  URL  --%>
            <div class="content-group">
                <div class="content-label">Kakao URL</div>
                <div class="content-value">
                    <a href="https://open.kakao.com/o/some-url" target="_blank">https://open.kakao.com/o/some-url</a>
                </div>
            </div>

            <%--   주소   --%>
            <div class="content-group">
                <div class="content-label">Address</div>
                <div class="content-value">양덕동 123-45번지</div>
            </div>

            <%--   이미지  --%>
            <div class="content-group">
                <div class="content-label">Image</div>
                <div class="content-value">
                    <img src="https://via.placeholder.com/300" alt="Uploaded Image">
                </div>
            </div>
        </div>
        <div class="card-footer text-center">
            <a href="#" class="btn btn-secondary">Back to List</a>
        </div>
    </div>
</div>
</body>
</html>