<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="shop item form tag" pageEncoding="UTF-8"%>
<link rel="stylesheet" href='<c:url value="/style/achievement_edit.css"/>' type="text/css">

<form>
    <div class="achieve">
        <div>
            <input type="text" name = "description" placeholder="Описание достижения"/>
        </div>
        <div>
            <input type="text" name = "type" placeholder="Тип действия"/>
        </div>
        <div class="value_achieve">
            <input type="hidden" name = "achievement-id" value= "-1" />
            <input type="number" name = "value" placeholder="Необходимое значение"/>
        </div>
        <button formaction="${pageContext.request.contextPath}/admin" formmethod="post"
                type="submit" name="add" value="add" class="btn btn-dark">Добавить</button>
    </div>
</form>
