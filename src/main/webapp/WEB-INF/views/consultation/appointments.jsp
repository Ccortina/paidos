<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<!-- Load Urls -->
<c:url var="flexigrid" value="/resources/js/flexigrid-1.1.js" />
<c:url var="flexigridCss" value="/resources/CSS/flexigrid.css" />

<!-- Load flexigrid-->
<script src="${flexigrid}" type="text/javascript"></script>

<link href="${flexigridCss}" rel="stylesheet" />

<html>
<head>
	<title>Home</title>
</head>
<body>
    
    <h1>Prueba 3</h1>
    
    <table id="flexme3"></table>
    
    <script type="text/javascript">
    	jQuery(document).ready(function($){
    		$('#flexme3').flexigrid({
    			url:'jsonAppointment',
    			dataType:'json',
    			colModel : [
    			    		{display: 'Fecha', name : 'date', sortable : false, align: 'center'},
    			    		{display: 'Last Name', name : 'lastName', sortable : false, align: 'center'},
    			    		],
    			    	buttons : [
    			    		{name: 'Add', bclass: 'add'},
    			    		{name: 'Delete', bclass: 'delete'},
    			    		{separator: true}
    			    		],
    			    	searchitems : [
    			    		{display: 'First Name', name : 'firstName'},
    			    		{display: 'Last Name', name : 'lastName', isdefault: true}
    			    		],
    			    	sortname: "iso",
    			    	sortorder: "asc",
    			    	usepager: true,
    			    	title: 'Familiares',
    			    	useRp: false,
    			    	rp: 15,
    			    	showTableToggleBtn: true,
    			    	width: 700,
    			    	height: 200
    		});
    	});
    		
    </script>
</body>
</html>
