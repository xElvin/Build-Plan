<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 13.09.2019
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<div id="roomParseId" style="margin-top: 7rem; margin-right: 15rem; margin-left: 18rem;">
    <c:forEach items="${rooms}" var="r" varStatus="Count">
        <div id="${r.id}" class="hoverFloor btn-primary border border-info rounded shadow p-3 mb-5 bg-white rounded">
            Room: ${r.name} <br>
                <div style="box-sizing: border-box">
                    <c:forEach items="${users}" var="u" varStatus="Number">
                        <c:if test="${u.room.name == r.name}">
                            <p id="personBtnId" onclick="callModal(${u.id})">${Number.count}. ${u.name} ${u.surname}</p>
                        </c:if>
                    </c:forEach>
                </div>
        </div>
    </c:forEach>
</div>
