<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 16.09.2019
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>

<c:forEach items="${rooms}" var="r">
    <option value="${r.id}">${r.name}</option>
</c:forEach>
