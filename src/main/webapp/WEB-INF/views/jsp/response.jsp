<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Basic Facebook Auth</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="#">Basic Facebook Authentication Project</a>
	</div>
  </div>
</nav>
 
<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
    </p>
	</div>
</div>
 
<div class="container">
    <h2>Response from Facebook</h2>
	<p><b>Code</b>: ${code}</p>
	<hr/>
	<p>
		<b>Access Token: ${tokenAccessURI}</b><br/>
		Token: ${token.access_token}<br/>
		Type: ${token.token_type}<br/>
		Expiry: ${token.expires_in}
		
	</p>
	<hr/>
	<p>
		<b>Token Debug: ${tokenDebugURI}</b><br/>
		${td}
	</p>
	<hr/>
	<p>
		<b>User Profile: ${graphURI}</b><br/>
		${profile}
	</p>
		
  <footer>
	<p>&copy; Vivek Kant 2018</p>
  </footer>
</div>
 
<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>