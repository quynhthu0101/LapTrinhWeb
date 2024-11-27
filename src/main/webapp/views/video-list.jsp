<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<select id="dropdown" name="dropdown" onchange="location.href='${pageContext.request.contextPath}/videos?id=' + this.value;">
    <c:forEach var="cate" items="${listcate}">
        <option value="${cate.categoryId}" 
            <c:if test="${cate.categoryId == param.id}">selected</c:if>>
            ${cate.categoryName}
            <!-- param.id: Đây là tham số id được truyền từ URL  --> 
        </option>
    </c:forEach>
</select>


<table border="1" width=100%>
	<tr>
		<th>STT</th>
		<th>VideoId</th>
		<th>Title</th>
		<th>Poster</th>
		<th>Views</th>
		<th>Description</th>
		<th>Active</th>
	</tr>

	<c:forEach items="${listvideo}" var="video" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${video.videoId }</td>
			<td>${video.title }</td>
			<td>
			
				<c:if test="${book.cover_image.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
					
				</c:if> 
				<c:if test="${book.cover_image.substring(0,5) == 'https' }">
					<c:url value="${video.poster}" var="imgUrl"></c:url>
				</c:if> 
					<img height="150" width="200" src="${imgUrl}" />
			
			</td>
			<td>${video.views }</td>
			<td>${video.description }</td>
			<td>
				<c:if test="${video.active == 1}">
					<span>Hoạt động</span>
				</c:if>
				<c:if test="${video.active != 1}">
					<span>Khoá</span>
				</c:if>
			
			</td>
			<td>
			       <c:if test="${isAdmin == '1' }">
			            <a href="${pageContext.request.contextPath}/admin/video/detail?id=${video.videoId}" class="center">View detail</a>
			       </c:if> 
			       <c:if test="${isAdmin == '0' }">
			            <a href="${pageContext.request.contextPath}/video/detail?id=${video.videoId}" class="center">View detail</a>
			       </c:if> 
			</td>


		</tr>
	</c:forEach>

</table>


