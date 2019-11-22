<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<link rel= "stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/normalize/3.0.3/normalize.min.css" />
	<link rel= "stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.0.0/magnific-popup.min.css" />
	<script src= "https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.min.js" ></script >
	<script src= "https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.0.0/jquery.magnific-popup.min.js" ></script >
	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
	<script src = "<%=application.getContextPath()%>/resources/js/app.js"></script>
	<script src = "<%=application.getContextPath()%>/resources/js/cmm/router.js"></script>
</head>
<body>
<div id = "wrapper"></div>
	<script>
		app.run('<%=application.getContextPath()%>');
	</script>
</body>
</html>