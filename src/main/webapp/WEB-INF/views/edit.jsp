<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Post</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function submitForm(event) {
            event.preventDefault();

            const postData = {
                id: ${post.id}, // 수정 대상 게시글 ID
                title: document.getElementById('title').value,
                contents: document.getElementById('contents').value,
                address: document.getElementById('address').value,
                kakaoUrl: document.getElementById('kakaoUrl').value,
                status: document.querySelector('input[name="status"]:checked').value
            };

            // 수정
            fetch("${pageContext.request.contextPath}/posts/" + postData.id, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(postData)
            }).then(response => {
                if (response.ok) {
                    alert("수정이 완료되었습니다.");
                    window.location.href = "${pageContext.request.contextPath}/posts/list";
                } else if (response.status === 403) {
                    alert("글 작성자만 수정이 가능합니다!");
                } else if (response.status === 401) {
                    alert("로그인이 필요합니다.");
                } else {
                    response.text().then(error => {
                        alert("수정 실패: " + error);
                    });
                }
            }).catch(error => console.error("Error:", error));
        }
    </script>
</head>
<body>
<section class="main-banner">
    <%--  배경 이미지 --%>
    <img src="${pageContext.request.contextPath}/resources/img/room_2.jpg" alt="Room Image" class="background-image">

    <%-- header  --%>
    <div class="header-overlay">
        <jsp:include page="/WEB-INF/views/inc/header.jsp"/>
    </div>

    <%--  제목, 부제목  --%>
    <div class="text-overlay">
        <h1 class="banner-title">양도 글 수정</h1>
        <p class="banner-subtitle">등록한 정보를 수정하실 수 있습니다.</p>
    </div>
</section>

<%-- 수정 폼 : edit --%>
<div class="container">
    <form id="editForm" onsubmit="submitForm(event)">
        <%-- 제목 --%>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${post.title}" required>
        </div>

        <%-- 내용 --%>
        <div class="mb-3">
            <label for="contents" class="form-label">Contents</label>
            <textarea class="form-control" id="contents" name="contents" rows="3" required>${post.contents}</textarea>
        </div>

        <%-- 주소 --%>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" value="${post.address}">
        </div>

        <%-- 기존 파일 표시(파일 수정은 불가) --%>
        <div class="mb-3">
            <label for="fileUrl" class="form-label">Picture (집 사진을 첨부해주세요.)</label>
            <input type="file" class="form-control" id="fileUrl" name="multipartFile" multiple>
        </div>


        <%-- 카카오 URL --%>
        <div class="mb-3">
            <label for="kakaoUrl" class="form-label">Kakao URL</label>
            <input type="text" class="form-control" id="kakaoUrl" name="kakaoUrl" value="${post.kakaoUrl}" required>
        </div>

        <%-- 상태 --%>
        <div class="mb-3">
            <label class="form-label">양도 상태</label>
            <div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status1" value="REQUESTING_TRANSFER"
                    ${post.status == 'REQUESTING_TRANSFER' ? 'checked' : ''}>
                    <label class="form-check-label" for="status1">양도구해요</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status2" value="TRANSFERRING"
                    ${post.status == 'TRANSFERRING' ? 'checked' : ''}>
                    <label class="form-check-label" for="status2">양도해요</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status3" value="COMPLETED"
                    ${post.status == 'COMPLETED' ? 'checked' : ''}>
                    <label class="form-check-label" for="status3">양도완료</label>
                </div>
            </div>
        </div>

        <%-- 저장 버튼 --%>
        <div class="d-flex justify-content-between mt-4">
            <button type="button" class="btn btn-outline-primary c-fc-ppl" onclick="window.location.href='${pageContext.request.contextPath}/posts/list'">Cancel</button>
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form>
</div>

<%-- footer --%>
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
</body>
</html>