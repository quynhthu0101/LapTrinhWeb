<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<body>

	<c:choose>
		<c:when test="${sessionScope.account == null}">
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<a href="${pageContext.request.contextPath }/login">Đăng
							nhập</a> | <a href="${pageContext.request.contextPath }/register">Đăng Ký </a>
					<i class="search fa fa-search search-button"></i>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<a
						href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a>
						| <a href="${pageContext.request.contextPath }/logout">Đăng
							xuất </a>
							<i class="search fa fa-search search-button"></i>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
</body>
