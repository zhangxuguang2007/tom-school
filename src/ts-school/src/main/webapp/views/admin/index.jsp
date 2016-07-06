<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学校管理系统</title>
    
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/admin/portal.css" />

	<script type="text/javascript" src="${contextPath}/resources/jquery/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/shared/include-ext.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/shared/options-toolbar.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/shared/tom-school.js"></script>
    <script type="text/javascript">
        if(!Tom.School.path){
        	Tom.School.path = "${contextPath}";
        }
    </script>
    <script type="text/javascript" src="${contextPath}/resources/admin/index.js"></script>
</head>
<body>
    <span id="app-msg" style="display:none;"></span>
</body>
</html>