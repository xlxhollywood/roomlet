<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>List page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<%-- header --%>
<jsp:include page="/WEB-INF/views/inc/header.jsp"/>

<div class="container mt-4">
    <h2 class="mt-4">방 찾기</h2>

    <%--  검색 창  --%>
    <form action="list" method="get" class="mb-4 d-flex justify-content-end align-items-center gap-3">
        <div class="d-flex">
            <div class="me-2">
                <select name="searchType" class="form-select">
                    <option value="title">Title</option>
                </select>
            </div>
            <div class="me-2">
                <input type="text" name="keyword" class="form-control" placeholder="" value="" style="width: 200px;">
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>

    <table class="table table-striped">
        <thead>
        <tr class="table-primary">
            <th>#</th>
            <th>Title</th>
            <th>User</th>
            <th>Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            // mock data
            String[][] posts = {
                    {"8", "25-1 양도 구해요", "abcabc", "2024-12-07", "구해요"},
                    {"7", "양덕동 투룸", "user123", "2024-12-07", "양도 완료"},
                    {"6", "단기 양도 구합니다", "poi099", "2024-12-06", "구해요"},
                    {"5", "12월 마지막 주 단기양도 합니다", "ubx3", "2024-12-05", "양도 완료"},
                    {"4", "장성동 미니 투룸 양도", "kadhd11", "2024-12-04", "양도 해요"},
                    {"3", "양덕동 원룸 양도", "dkcns9", "2024-12-03", "양도 해요"},
                    {"2", "12/26-27 양덕 단기양도 구합니다!", "3json", "2024-12-02", "구해요"},
                    {"1", "양덕 원룸 내년 2월까지 양도해요", "cbxuc00", "2024-12-01", "양도 완료"}
            };

            for (int i = 0; i < posts.length; i++) {
        %>
        <tr>
            <td><%= posts[i][0] %></td>
            <td>
                <a href="${pageContext.request.contextPath}/posts/view" class="text-decoration-none" style="color: black">
                    <%= posts[i][1] %>
                </a>
            </td>
            <td><%= posts[i][2] %></td>
            <td><%= posts[i][3] %></td>
            <td>
                <% if ("양도 완료".equals(posts[i][4])) { %>
                <span style="color: #299a4d;"><%= posts[i][4] %></span>
                <% } else if ("구해요".equals(posts[i][4])) { %>
                <span style="color: #ca4040;"><%= posts[i][4] %></span>
                <% } else { %>
                <span style="color: #3e94d6;"><%= posts[i][4] %></span>
                <% } %>
            </td>
            <td>
                <div class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/posts/edit" class="btn btn-primary btn-sm">
                        Edit
                    </a>
                    <button class="btn btn-danger btn-sm" onclick="">
                        Delete
                    </button>
                </div>
            </td>

        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
