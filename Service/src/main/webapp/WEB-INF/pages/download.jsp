<%--
  Created by IntelliJ IDEA.
  User: QuangTV
  Date: 2/6/2015
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<html>
<head>
    <title> ${sessionScope.story} | Manga Book</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no">

    <script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript" language="javascript"></script>
    <c:url value="/res/css/css.css" var="cssUrl"/>
    <c:url value="/res/css/indexpage.css" var="indexCssUrl"/>
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${cssUrl}">
    <link type="text/css" rel="stylesheet" href="${indexCssUrl}">
</head>
<body>

<div id="menujohanes">
    <div style="top: 0px;" class="navbar" id="menu">
        <div class="row" style="padding-top: 10px;">
            <h1 class="three columns logo" id="logo">
                <a href="http://RoboManga.com/" style="color: #d04526;font-family: font-xis, tahoma, sans-serif;font-size: 34px;">
                    <img src="/res/image/icon.png" style="width:40px;">
                    RoboManga
                </a>
            </h1>
            <ul>
                <li><a href="http://RoboManga.com/" title="Home" class="title">Home</a></li>
                <li><a href="#/vn/0">Vietnamese</a></li>
                <li><a href="#/en/0">English</a></li>
                <li><a href="#/jp/0">Japanese Raw</a></li>
                <li><a href="#">About Us</a></li>

            </ul>
        </div>
    </div>
</div>

<div class="row block_content" id="block_search">
    <li class="append field eight columns" style="width: 64.95745%;">
        <input class="xwide text input" id="search_input" type="text" placeholder="Nhập tên manga hoặc tên tác giả">
        <span class="adjoined"><i class="icon-search" id="bt_seacrch" onclick="search();"
                                  style="margin-top: -1px;color: #f4b350;"></i></span>

    </li>

    </ul>
			<span class="text-center four columns" style="line-height: 34px;width: 32.91489%;">
				<span class="upper">Thế giới truyện tranh </span>
				<span class="big_text hi_light"> RoboManga</span>
			</span>
</div>

<c:url value="/res/xml/chapter.xml" var="xmlUrl"/>
<c:url value="/res/xml/chapter.xsl" var="xslUrl"/>
<c:import url="${sessionScope.xmlPath}" var="xml"/>
<c:import url="${xslUrl}" var="xsl"/>


<div class=" text-center" id="div_image" style="display: block;">

    <x:transform xml="${xml}" xslt="${xsl}">
        <x:param name="chapter" value="${sessionScope.chapter}"/>
    </x:transform>

</div>


<div class="row">

    <p class="text-center" style="padding: 10px;">
        <a style="font-size:2rem;" href="/exportPDF?story=${sessionScope.story}&chapter=${sessionScope.chapter}" class="name_manga" target="_blank">Download ${sessionScope.story} PDF</a>
    </p>
</div>


<div class="row block_content">
    <span class="title_content"><i class="icon-info-circled"></i>Thông tin website</span>

    <p><strong>Chủ sở hữu: </strong><span class="hi_light">RoboManga</span></p>

    <p><strong>Email: </strong><span class="hi_light">RoboManga@gmail.com</span></p>

    <p><strong>Fanpage: </strong><a href="https://www.facebook.com/pages/Otaku-Fan-Club/735962693161234?fref=ts"
                                    style="color: #f4b350;">QKH Team</a></p>

    <p><strong>Phát triển bởi: </strong><a href="https://www.facebook.com/pages/Otaku-Fan-Club/735962693161234?fref=ts"
                                           style="color: #f4b350;">KhangTN</a></p>

    <p><span class="hi_light"> Copyright © 2015 Manga Book - Design By QKH team -</span></p>
</div>

</body>
</html>
