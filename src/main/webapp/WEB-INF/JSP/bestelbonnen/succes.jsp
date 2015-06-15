<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Bevestiging' />
</head>
<body>
	<a href="<c:url value='/index.htm'/>">Terug naar overzicht</a>
	<h1>Je mandje is bevestigd als bestelbon ${bestelbonId}</h1>
</body>
</html>