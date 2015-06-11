<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Wereldwijnen' />
</head>
<body>
	<header>
		<h1>Wereldwijnen</h1>
		<c:forEach items='${landen}' var='land'>
			<c:url value='' var='url'>
				<c:param name='landid' value='${land.id}' />
			</c:url>
			<a href='${url}'><img
				src='<c:url value="/images/${land.id}.png"/>' alt='${land.naam}' /></a>
		</c:forEach>
	</header>
</body>
<c:if test="${not empty soorten}">
	<c:forEach items='${soorten}' var='soort'>
		<c:url value='' var='url'>
			<c:param name='soortid' value='${soort.id}' />
		</c:url>
		<a href='${url}'>${soort.naam}</a>
	</c:forEach>
</c:if>
</html>