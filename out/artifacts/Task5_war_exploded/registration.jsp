
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <link rel = stylesheet href="style.css" type="text/css">
</head>
<body>
<form action="registration" method="POST">
    <div class = "my_block">
    Email: <input type = "text" name="email" />
    Name: <input type = text name="username" />
    Age: <input name="userage" />
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
    <input type="submit" value="Зарегистрироваться" />
    </div>
</form>
</body>
</html>