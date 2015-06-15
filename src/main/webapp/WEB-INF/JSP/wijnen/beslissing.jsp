<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Beslissing' />
</head>
<body>
	<a href="<c:url value='/index.htm'/>">Terug naar overzicht</a>
	<h1>Wijn bestaat al in het mandje, maak een keuze:</h1>
	<form method='post' id='toevoegform'><input name='wijnid'
		value='${id}' type='hidden' /> <input name='aantal'
		value='${aantal}' type='hidden' /> <input type='submit'
		value='Aantal toevoegen' name='toevoegknop'> <input
		type='submit' value='Aantal vervangen' name='toevoegknop'></form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>