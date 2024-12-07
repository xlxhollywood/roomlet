<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Post</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<%-- header --%>
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>

<div class="card">
    <h4 class="card-title text-center mb-4">Make a request</h4>
    <form action="/posts" method="POST" enctype="multipart/form-data">
        <%-- 제목 --%>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter post title" required>
        </div>

        <%-- 상세 설명 --%>
        <div class="mb-3">
            <label for="contents" class="form-label">Contents</label>
            <textarea class="form-control" id="contents" name="contents" rows="5" placeholder="Describe the details"
                      required></textarea>
        </div>

        <%--    카카오톡 url    --%>
        <div class="mb-3">
            <label for="kakaoUrl" class="form-label">Kakao URL</label>
            <input type="url" class="form-control" id="kakaoUrl" name="kakaoUrl" placeholder="Enter Kakao URL" required>
        </div>

        <%-- 주소 --%>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="Enter address">
        </div>

        <%--  상세한 집 사진  --%>
        <div class="mb-3">
            <label for="fileUrl" class="form-label">Upload Image</label>
            <input type="file" class="form-control" id="fileUrl" name="fileUrl">
        </div>

        <%--  status  --%>
        <div class="mb-3">
            <label class="form-label">Status</label>
            <div class="d-flex align-items-center">
                <%--  양도 구해요  --%>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="status" id="requestingTransfer"
                           value="REQUESTING_TRANSFER" required>
                    <label class="form-check-label" for="requestingTransfer">양도 구해요</label>
                </div>

                <%-- 양도 해요 --%>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="status" id="transferring" value="TRANSFERRING">
                    <label class="form-check-label" for="transferring">양도 해요</label>
                </div>

                <%--  완료  --%>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="status" id="completed" value="COMPLETED">
                    <label class="form-check-label" for="completed">양도 완료</label>
                </div>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
