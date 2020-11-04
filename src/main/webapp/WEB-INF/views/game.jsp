<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  entities.User: Alex
  Date: 11.10.2020
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='<c:url value="/style/game.css"/>' type="text/css">
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body class="my_body">
<t:header/>
<div class="my_container">
    <t:leftblock/>
    <div class="my-center-block" id = "gameblock" onclick="GetScore('${pageContext.request.contextPath}')">
        <div class = "circle" id = "circle">
            <div class = eye id = "eye1">
                <div class = pupil id = "pupil1"></div>
            </div>
            <div class = eye>
                <div class = pupil id = "pupil2"></div>
            </div>
        </div>
    </div>
    <div class="my-right-block"  id = 'right-block'>
        <div class = "cube" id = 'cube'></div>
    </div>
</div>

<t:footer footerText="Текст в футере Стартовой страницы"></t:footer>
</body>
<script type="text/javascript" src ="${pageContext.request.contextPath}/js/script.js">
</script>
<script type="text/javascript" src ="${pageContext.request.contextPath}/js/game.js">
</script>
</html>