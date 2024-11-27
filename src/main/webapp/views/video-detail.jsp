<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<table border="1" width=100%>

		<tr>
			
			<td>
			
				<c:if test="${video.poster.substring(0,5) != 'https' }">
					<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
					
				</c:if> 
				<c:if test="${video.poster.substring(0,5) == 'https' }">
					<c:url value="${video.poster}" var="imgUrl"></c:url>
				</c:if> 
					<img height="150" width="200" src="${imgUrl}" />
			
			</td>
			<td>Tiêu đề: ${video.title}<br />
                Mã video: ${video.videoId}<br />
                Category name: ${cate.categoryName} <br />
                View: ${video.views} <br />
                Share: ${share} <br />
                Like: ${like} <br />
							
			</td>
		</tr>

</table>