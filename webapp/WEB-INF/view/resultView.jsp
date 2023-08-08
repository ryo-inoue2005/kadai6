<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>結果表示画面</title>
</head>
<body>

	<h1>運勢結果</h1>

	${f:h(birthday)}
	
	<p>${f:h(omikuji.disp()}</p>
	<p>${f:h(omikuji.negaigoto)}</p>
	<p>${f:h(omikuji.akinai)}</p>
	<p>${f:h(omikuji.gakumon)}</p>
	
	
<%-- 
	<c:forEach var="omikuji" items="${omikujiList}">
		<tr>
			<td>${omikuji.omikujiCode}</td>
		</tr>
	</c:forEach> --%>

	<html:link action="/form">占いフォームに戻る</html:link>
	<html:link action="/pastList">過去のおみくじ結果を見る</html:link>
	<html:link action="/stats">統計を見る</html:link>


</body>
</html:html>