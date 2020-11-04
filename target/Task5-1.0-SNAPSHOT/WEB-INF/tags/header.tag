<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="header form tag" pageEncoding="UTF-8"%>
<link rel="stylesheet" href='<c:url value="/style/header.css"/>' type="text/css">
<header class = "my_header">
    <div class = "main_button">
    <a href="${pageContext.request.contextPath}" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" >Clicker Game</a>
    </div>
    <div class = "center-buttons-block">
        <button type="button" name="color-button" class="btn btn-primary">Primary</button>
        <button type="button" name="color-button" class="btn btn-danger">Danger</button>
        <button type="button" name="color-button" class="btn btn-warning">Warning</button>
        <button type="button" name="color-button" class="btn btn-dark">Next</button>
    </div>
    <div class = "right-buttons-block">
        <c:if test="${sessionScope.get('authorized') != 'true'}">
            <a href="${pageContext.request.contextPath}/registration" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" >Регистрация</a>
            <a href="${pageContext.request.contextPath}/authorization" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" >Вход</a>
        </c:if>
        <c:if test="${sessionScope.get('authorized') == 'true'}">
            <div class = "account"><p>Ваш счёт :  <div id = 'scoreCount'>${sessionScope.get('score')}</div></p>
            </div>
            <a href="${pageContext.request.contextPath}/profile" class="btn btn-primary btn-lg active" role="button" aria-pressed="true" >Профиль</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-lg active" role="button" aria-pressed="true" >Выйти</a>
        </c:if>
    </div>
</header>
