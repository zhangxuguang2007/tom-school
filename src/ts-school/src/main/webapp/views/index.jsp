<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Portal Layout Sample</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="${contextPath}/resources/jquery/jquery-3.0.0.min.js"></script>
	
	<!-- ext -->
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/ext4.0/resources/css/ext-all.css" />
	<script type="text/javascript" src="${contextPath}/resources/ext4.0/ext-all.js"></script>
	
	<!-- shared -->
	<script type="text/javascript" src="${contextPath}/resources/shared/examples.js"></script>
	
	<!-- page -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/admin/portal.css" />
	<script type="text/javascript" src="${contextPath}/resources/admin/portal.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/admin/classes.js"></script>
    <script type="text/javascript">
        Ext.Loader.setPath('Ext.app', 'classes');

        Ext.require([
            'Ext.layout.container.*',
            'Ext.resizer.Splitter',
            'Ext.fx.target.Element',
            'Ext.fx.target.Component',
            'Ext.window.Window',
            'Ext.app.Portlet',
            'Ext.app.PortalColumn',
            'Ext.app.PortalPanel',
            'Ext.app.Portlet',
            'Ext.app.PortalDropZone',
            'Ext.app.GridPortlet',
            'Ext.app.ChartPortlet'
        ]);
		
		$(function(){
			Ext.create('Ext.app.Portal');
		});
    </script>
<title>学校管理系统</title>
</head>
<body>
    <span id="app-msg" style="display:none;"></span>
</body>
</html>