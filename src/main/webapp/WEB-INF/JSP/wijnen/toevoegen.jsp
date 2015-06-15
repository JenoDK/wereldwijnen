<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Toevoegen' />
</head>
<body>
	<h1>Wijn toevoegen aan mandje</h1>
	<a href="<c:url value='/index.htm'/>">Terug naar overzicht</a>
	<c:if test="${not empty wijn}">
		<dl>
			<dt>Land</dt>
			<dd>${wijn.soort.land.naam}</dd>
			<dt>Soort</dt>
			<dd>${wijn.soort.naam}</dd>
			<dt>Jaar</dt>
			<dd>${wijn.jaar}</dd>
			<dt>Beoordeling</dt>
			<dd><c:forEach begin='1' end='${wijn.beoordeling}'>
			&#9733; 
					</c:forEach></dd>
			<dt>Prijs</dt>
			<dd>€${wijn.prijs}</dd>
		</dl>
	</c:if>
	<form method='post' id='toevoegform'><label>Aantal
			flessen: <span>${fout}</span> <input name='aantal'
			value='${param.aantal}' type='number' min="1" required>
	</label> <input type="hidden" name='id' value="${wijn.id}" />
				<input type='submit' value='Toevoegen' id='toevoegknop'>
			</form>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>