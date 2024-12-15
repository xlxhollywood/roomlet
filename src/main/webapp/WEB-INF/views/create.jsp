<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Post</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function submitForm(event) {
            event.preventDefault();

            // json 변환
            const postData = {
                title: document.getElementById('title').value,
                contents: document.getElementById('contents').value,
                address: document.getElementById('address').value,
                kakaoUrl: document.getElementById('kakaoUrl').value,
                status: document.querySelector('input[name="status"]:checked').value
            };

            const formData = new FormData();
            formData.append("post", JSON.stringify(postData)); // JSON 데이터를 'post' 파라미터로 추가

            // 파일 업로드
            const files = document.getElementById('fileUrl').files;
            for (let i = 0; i < files.length; i++) {
                formData.append("multipartFile", files[i]);
            }

            // post로 전송
            fetch("${pageContext.request.contextPath}/posts", {
                method: "POST",
                body: formData
            }).then(response => {
                if (response.ok) {
                    alert("게시 성공!");
                    window.location.href = "${pageContext.request.contextPath}/posts/list";
                } else {
                    alert("게시에 실패하였습니다.");
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
        <h1 class="banner-title">새 양도 글 작성</h1>
        <p class="banner-subtitle">원하는 집이 없거나 내 집을 양도하고 싶다면 이곳에서 등록하세요</p>
    </div>
</section>

<%-- 입력 폼 : create--%>
<div class="container">
    <form id="createForm" onsubmit="submitForm(event)">
        <%--  제목  --%>
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요." required>
        </div>

        <%--  내용  --%>
        <div class="mb-3">
            <label for="contents" class="form-label">Contents</label>
            <textarea class="form-control" id="contents" rows="3"
                      placeholder="집 상태, 월세 등에 대한 정보를 남겨주세요." required></textarea>
        </div>

        <%--  주소  --%>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" placeholder="주소를 남겨주세요.">
        </div>

        <%--  집 사진  --%>
        <div class="mb-3">
            <label for="fileUrl" class="form-label">Picture (집 사진을 첨부해주세요.)</label>
            <input type="file" class="form-control" id="fileUrl" name="multipartFile" multiple>
        </div>

        <%--   kakao url  --%>
        <div class="mb-3">
            <label for="kakaoUrl" class="form-label">kakao URL</label>
            <input type="text" class="form-control" id="kakaoUrl" placeholder="카카오톡 채팅방 링크를 남겨주세요." required>
        </div>

        <%--  status  --%>
        <div class="mb-3">
            <label class="form-label">양도 상태</label>
            <div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status1" value="REQUESTING_TRANSFER"
                           checked>
                    <label class="form-check-label" for="status1">양도구해요</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status2" value="TRANSFERRING">
                    <label class="form-check-label" for="status2">양도해요</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="status" id="status3" value="COMPLETED">
                    <label class="form-check-label" for="status3">양도완료</label>
                </div>
            </div>
        </div>

        <%--  button  --%>
        <div class="d-flex justify-content-between mt-4">
            <button type="button" class="btn btn-outline-primary c-fc-ppl" onclick="window.location.href='${pageContext.request.contextPath}/posts/list'">Cancel</button>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

<%-- footer --%>
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>
</body>
</html>