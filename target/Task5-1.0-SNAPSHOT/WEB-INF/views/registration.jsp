<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href='<c:url value="/style/style.css"/>' type="text/css">
    <link rel="stylesheet" href='<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>' integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src='<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"/>' integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body class="my_body">
<t:header/>
<div class="my_container">
    <t:leftblock/>
    <div class="my-center-block">
        <form action="registration" method="POST" >
            <div class="data-form">
                Email: <input type = "text" name="email" />
                Name: <input type = "text" name="name" />
                Password: <input type = "password" name="password" />
                <div>Gender: <input type="radio" name="gender" value="female" checked />Female
                    <input type="radio" name="gender" value="male" />Male
                </div>
                Country: <select name="country">
                <option>Spain</option>
                <option>France</option>
                <option>Germany</option>
                <option>Russia</option>
                </select>
                <br>
                <div class="data-button">
                    <input type="submit" value="Зарегистрироваться" name="color-button" class="btn btn-dark"/>
                </div>
                <c:if test="${requestScope.get('registered') == 'false'}">
                    <p class = 'wrong-data'>${requestScope.get('failedDescription')}</p>
                </c:if>
                <c:if test="${requestScope.get('registered') == 'true'}">
                    <p class = 'user-registered'>Поздравляем, вы успешно зарегистрированы</p>
                </c:if>
            </div>
        </form>
    </div>
    <div class="my-right-block"  id = 'right-block'>
        <div class = "cub"></div>
    </div>
</div>
<t:footer footerText="Футер регистрации как то так"/>
    <script type="text/javascript" src ="${pageContext.request.contextPath}/js/script.js">
    </script>
</body>

</html>