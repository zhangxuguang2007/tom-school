<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Portal Layout Sample</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/admin/portal.css" />

	<!-- jquery -->
	<script type="text/javascript" src="${contextPath}/resources/jquery/jquery-3.0.0.min.js"></script>
	
	<!-- shared -->
	<script type="text/javascript" src="${contextPath}/resources/shared/include-ext.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/shared/options-toolbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/shared/examples.js"></script>

    <!-- page -->
    <script type="text/javascript">
        Ext.Loader.setPath('Ext.app', '${contextPath}/resources/admin/classes');
    </script>
    <script type="text/javascript" src="${contextPath}/resources/admin/portal.js"></script>
    <script type="text/javascript">
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

        Ext.onReady(function(){
            Ext.create('Ext.app.Portal');
        });
		
		$(function(){
		});
    </script>
<title>学校管理系统</title>
</head>
<body>
    <span id="app-msg" style="display:none;"></span>
</body>
</html>