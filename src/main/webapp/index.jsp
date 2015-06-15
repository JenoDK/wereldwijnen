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
		<h1>Wereldwijnen</h1> <c:forEach items='${landen}' var='land'>
			<c:url value='' var='url'>
				<c:param name='landid' value='${land.id}' />
			</c:url>
			<a href='${url}'><img
				src='<c:url value="/images/${land.id}.png"/>' alt='${land.naam}' /></a>
		</c:forEach><br /> <c:if test="${not empty wijnenInMandje}">
			<a href='<c:url value='/wijnen/mandje.htm' />'><img src='<c:url value="/images/mandje.png"/>'
				alt='mandje' /></a>
		</c:if>
</header>
</body>
<div class="clearFix" id="left-sidebar"><c:if
		test="${not empty soorten}">
		<h2>Soorten uit ${soorten[0].land.naam}</h2>
		<ul>
			<c:forEach items='${soorten}' var='soort'>
				<c:url value='' var='url'>
					<c:param name='landid' value='${soort.land.id}' />
					<c:param name='soortid' value='${soort.id}' />
				</c:url>
				<li><a href='${url}'>${soort.naam}</a></li>
			</c:forEach>
		</ul>
	</c:if></div>
<div class="clearFix" id="main-content"><c:if
		test="${not empty wijnen}">
		<h2>Wijnen uit ${wijnen[0].soort.naam}</h2>
		<ul>
			<c:forEach items='${wijnen}' var='wijn'>
				<c:url value='/wijnen/toevoegen.htm' var='url'>
					<c:param name='wijnid' value='${wijn.id}' />
				</c:url>
				<li><a href='${url}'>${wijn.jaar}</a> <c:forEach begin='1'
						end='${wijn.beoordeling}'>
			&#9733;
					</c:forEach></li>
			</c:forEach>
		</ul>
	</c:if></div>
</html>