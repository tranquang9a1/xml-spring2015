<%--
  Created by IntelliJ IDEA.
  User: QuangTV
  Date: 2/6/2015
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<html>
<head>
    <title> ${sessionScope.story} | Manga Book</title>
</head>
<body>
    Story: ${sessionScope.story} <br/>
    Chapter: ${sessionScope.chapter} <br/>
    <c:url value="/res/chapter.xml" var="xmlUrl" />
    <c:url value="/res/chapter.xsl" var="xslUrl" />
    <c:import url="${sessionScope.xmlPath}" var="xml" />
    <c:import url="${xslUrl}" var="xsl" />

    <x:transform xml="${xml}" xslt="${xsl}" >
        <x:param name="chapter" value="${sessionScope.chapter}" />
    </x:transform>

    <a href="/exportPDF?story=${sessionScope.story}&chapter=${sessionScope.chapter}" />Download PDF File </a>



</body>
</html>
