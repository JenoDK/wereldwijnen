<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Mandje' />
</head>
<body>
	<h1>Mandje</h1>
	<a href="<c:url value='/index.htm'/>">Terug naar overzicht</a>
	<c:if test="${not empty wijnenInMandje}">
		<form name='form' action="<c:url value='/wijnen/mandje.htm'/>"
			method='post' id='toevoegform'>
			<table>
				<thead>
					<tr>
						<th>Wijn</th>
						<th>Prijs</th>
						<th>Aantal</th>
						<th>Te betalen</th>
						<th><input type='submit' value='Verwijderen'
							name='verwijderknop'></th>
				</tr>
			</thead>
				<tbody>
					<c:forEach items='${wijnenInMandje}' var='wijn'>
						<tr>
							<td>${wijn.soort.land.naam}<br />${wijn.soort.naam}<br />${wijn.jaar}</td>
							<td><fmt:formatNumber value='${wijn.prijs}'
									minFractionDigits='2' maxFractionDigits='2' /></td>
							<td>${wijn.aantal}</td>
							<td><fmt:formatNumber value='${wijn.totaal}'
									minFractionDigits='2' maxFractionDigits='2' /></td>
							<td><label> <input type='checkbox' name='id'
									value='${wijn.id}'>
							</label></td>
						</tr>
					</c:forEach>
			</tbody>
				<caption>Totaal: <fmt:formatNumber value='${mandjeTotaal}'
						minFractionDigits='2' maxFractionDigits='2' /></caption>
		</table>
		</form>



		<form method='post'
			action="<c:url value='/bestelbonnen/toevoegen.htm'/>"
			id='toevoegform'><label>Naam:<span>${fouten.voornaam}</span>
				<input name='naam' value='${param.naam}' type='text' autofocus
				required></label> <label>Straat: <span>${fouten.straat}</span>
				<input name='straat' value='${param.straat}' type='text' required></label>

			<label>Huisnummer:<span>${fouten.huisnummer}</span> <input
				name='huisnummer' value='${param.huisnummer}' required type='text'></label>

			<label>Postcode:<span>${fouten.postcode}</span> <input
				name='postcode' value='${param.postcode}' required type='number' min='1000' max='9999'></label>

			<label>Gemeente:<span>${fouten.gemeente}</span> <input
				name='gemeente' value='${param.gemeente}' required type='text'></label>

			<div><label><span>${fouten.bestelwijze}</span> <input
					type='radio' name='bestelwijze' value='AFHALEN'
					${param.geslacht=='AFHALEN' ? 'checked' : ''} required>Afhalen</label>
		</div>
			<div><label><input type='radio' name='bestelwijze'
					value='OPSTUREN' ${param.geslacht=='OPSTUREN' ? 'checked' : ''}
					required>Opsturen</label></div> <input type='submit'
			value='Als bestelbon bevestigen' id='toevoegknop'></form>


	</c:if>
	<c:if test="${empty wijnenInMandje}">
		<h2>Mandje is leeg</h2>
	</c:if>
	<script>
		document.getElementById('toevoegform').onsubmit = function() {
			document.getElementById('toevoegknop').disabled = true;
		};
	</script>
</body>
</html>