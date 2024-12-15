<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Page</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        // 게시글 수정
        function editPost(postId) {
            window.location.href = "${pageContext.request.contextPath}/posts/edit?id=" + postId;
        }

        // 게시글 삭제 요청
        function deletePost(postId) {
            if (confirm("삭제하시겠습니까?")) {
                fetch("${pageContext.request.contextPath}/posts/" + postId, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).then(response => {
                    if (response.ok) {
                        // 삭제 성공
                        alert("삭제되었습니다.");
                        window.location.reload();
                    } else if (response.status === 403) {
                        // 권한 x
                        alert("글 작성자만 삭제가 가능합니다!");
                    } else {
                        // 기타 오류
                        response.text().then(error => {
                            alert("삭제 실패: " + error);
                        });
                    }
                }).catch(error => {
                    console.error("Error:", error);
                    alert("오류가 발생했습니다. 다시 시도해주세요.");
                });
            }
        }

        document.addEventListener("DOMContentLoaded", () => {
            const searchButton = document.getElementById("searchButton");
            const searchInput = document.getElementById("searchInput");
            const tableBody = document.querySelector("tbody");

            searchButton.addEventListener("click", () => {
                const keyword = searchInput.value.trim();
                if (keyword === "") {
                    alert("검색어를 입력해주세요.");
                    return;
                }

                // 검색 요청
                fetch(`/posts/search?keyword=${keyword}`)
                    .then((response) => {
                        if (!response.ok) {
                            throw new Error("검색 요청 실패");
                        }
                        return response.json();
                    })
                    .then((data) => {
                        renderPosts(data);
                    })
                    .catch((error) => {
                        console.error("Error:", error);
                        alert("검색 중 오류가 발생했습니다.");
                    });
            });

            function renderPosts(posts) {
                tableBody.innerHTML = ""; // 기존 내용을 초기화
                if (posts.length === 0) {
                    tableBody.innerHTML = "<tr><td colspan='6' class='text-center'>검색 결과가 없습니다.</td></tr>";
                    return;
                }

                posts.forEach((post, index) => {
                    const formattedDate = formatDate(post.createTime); // 날짜 포맷팅 함수
                    const row = `
                        <tr>
                          <td>${posts.length - index}</td>
                          <td>
                            <a href="/posts/view?id=${post.id}" class="text-decoration-none" style="color: #4f378b">
                              ${post.title}
                            </a>
                          </td>
                          <td>${post.memberId || "Unknown User"}</td>
                          <td>${formattedDate}</td>
                          <td>${getStatusBadge(post.status)}</td>
                          <td>
                            <button class="btn btn-sm" onclick="editPost(${post.id})">수정</button>
                            <button class="btn btn-sm" onclick="deletePost(${post.id})">삭제</button>
                          </td>
                        </tr>
                      `;
                    tableBody.insertAdjacentHTML("beforeend", row);
                });
            }

            function formatDate(dateString) {
                const date = new Date(dateString);
                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, "0");
                const day = String(date.getDate()).padStart(2, "0");
                return `${year}/${month}/${day}`;
            }

            function getStatusBadge(status) {
                switch (status) {
                    case "REQUESTING_TRANSFER":
                        return `<span class="badge text-bg-success">양도구해요</span>`;
                    case "TRANSFERRING":
                        return `<span class="badge text-bg-primary">양도해요</span>`;
                    case "COMPLETED":
                        return `<span class="badge text-bg-secondary">양도완료</span>`;
                    default:
                        return `<span class="badge text-bg-dark">미분류</span>`;
                }
            }
        });
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
        <h1 class="banner-title">Housing List</h1>
        <p class="banner-subtitle">내 집을 양도하고 싶거나 원하는 집을 구하고 싶다면</p>
    </div>
</section>

<%-- list --%>
<div class="container mt-5">
    <%-- 드롭다운(페이지 이동;), 검색 --%>
    <div class="d-flex justify-content-between align-items-center mb-4">

        <div class="dropdown">
            <select class="form-select" id="filterStatus" style="max-width: 200px;">
                <option value="ALL" selected>All</option>
                <option value="REQUESTING_TRANSFER">양도 구해요</option>
                <option value="TRANSFERRING">양도 해요</option>
                <option value="COMPLETED">양도 완료</option>
            </select>
        </div>

        <div class="input-group" style="max-width: 300px;">
            <input type="text" class="form-control" placeholder="Search by title" id="searchInput" aria-label="Search">
            <button class="btn btn-primary search-btn" id="searchButton" type="button">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
            </button>
        </div>
    </div>

    <%--  테이블  --%>
    <table class="table table-hover align-middle">
        <thead class="table-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">User</th>
            <th scope="col">Date</th>
            <th scope="col">Status</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${posts}" varStatus="status">
            <tr>
                <td>${posts.size() - status.index}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/posts/view?id=${post.id}" class="text-decoration-none" style="color: #4f378b">
                            ${post.title}
                    </a>
                </td>
                <td><c:if test="${not empty post.memberId}">
                    ${post.memberId}
                </c:if>
                    <c:if test="${empty post.memberId}">
                        Unknown User
                    </c:if></td>
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
                    <button class="btn btn-sm" onclick="editPost(${post.id})">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd"
                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
                        </svg>
                    </button>
                    <button class="btn btn-sm" onclick="deletePost(${post.id})">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-trash3-fill" viewBox="0 0 16 16">
                            <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
                        </svg>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- footer --%>
<jsp:include page="/WEB-INF/views/inc/footer.jsp"/>

</body>

</html>