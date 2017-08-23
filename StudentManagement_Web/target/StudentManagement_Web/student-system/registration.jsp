<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("user-id") == null) {
		response.sendRedirect("../index.html");
	}
	out.print("<input type='hidden' value='" + session.getAttribute("user-id") + "' id='hide'/>");
%>
<!doctype html>
<html lang="en" ng-app="myApp">
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Exam Registration</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />


<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
<link href="assets/css/popup.css" rel="stylesheet"/>
<link rel="canonical" href="http://www.alessioatzeni.com/wp-content/tutorials/jquery/login-box-modal-dialog-window/index.html" />
<!-- 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>
 -->

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	
<script>
	var test = document.getElementById("hide").value;

	var app = angular.module("myApp", []);

	app.controller('studentController', function($scope, $http) {
		//$scope.GetAllData = function() {
		$http.get("http://localhost:8089/student/serachStudents?stuId=3251117" +"&name=&telNo=&email=&acedemicYear=").then(
				function(response) {
					$scope.student = response.data;
				});
		//}
	});
	app.controller('notificationController', function($scope, $http) {
		$http.get("http://localhost:8089/notification/allNotification").then(
				function(response) {
					$scope.notification = response.data;
				});
	});
	
	app.controller('examController', function($scope, $http) {
		$http.get("http://localhost:8089/registration/registrations?stuId=test").then(
				function(response) {
					$scope.exams = response.data;
				});
	});
	
</script>

<script>
$(document).ready(function() {
	$("#registerForm").submit(function(e) {
		var username = $("#username").val();
		var regNo = $("#regNo").val();
		var year = $("#year").val();
		var sem = $("#sem").val();
		
		var url = "http://localhost:8089/registration/saveRegistration";

		
			
	           $.ajax({
				      headers : 
				     {
					   'Accept' : 'application/json',
					   'Content-Type' : 'application/json'
				     },
				      type : "POST",
				      url : url,
				      data : JSON.stringify(
				    {
					   "name" : username,
					   "regNo" : regNo,
					   "year" : year,
					   "semester" : sem
				    }),

				    success : function(data) {
					  alert("successfully Saved");
					  location.reload();
				  }
			  });

			e.preventDefault();

		});

		$("#close").click(function(e) {
			/* location.reload(); */
			$('#mask , .login-popup').fadeOut(300);
		});
	});
</script>
</head>
<body>

	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="assets/img/sidebar-5.jpg">

			<!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


			<div class="sidebar-wrapper">
				<div class="logo">
					<a href="" class="simple-text">
						STUDENT'S SITE </a>
				</div>

				<ul class="nav">
					<li><a href="dashboard.jsp"> <i class="pe-7s-graph"></i>
							<p>Dashboard</p>
					</a></li>
					<li><a href="user.jsp"> <i class="pe-7s-user"></i>
							<p>User Profile</p>
					</a></li>
					<li><a href="table.jsp"> <i class="pe-7s-note2"></i>
							<p>Table List</p>
					</a></li>
					<li ><a href="result.jsp"> <i
							class="pe-7s-news-paper"></i>
							<p>My Result</p>
					</a></li>
					<li><a href="feedback.jsp"> <i class="pe-7s-science"></i>
							<p>Your Feedback</p>
					</a></li>
					<!-- <li><a href="maps.html"> <i class="pe-7s-map-marker"></i>
							<p>Maps</p>
					</a></li> -->
					<li class="active"><a href="registration.jsp"> <i class="pe-7s-bell"></i>
							<p>Registration</p>
					</a></li>
					<li class="active-pro"><a href="https://www.sensiple.com/"> <i
							class="pe-7s-rocket"></i>
							<p>SENSIPLE Library</p>
					</a></li>
				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default navbar-fixed">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#navigation-example-2">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Registration</a>
					</div>
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav navbar-left">
							<li><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-dashboard"></i>
							</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-globe"></i> <b
									class="caret"></b> <span class="notification">5</span>
							</a>
								<ul class="dropdown-menu" ng-controller="notificationController">
									<li ng-repeat="n in notification"><a href="#">{{
											n.message }}</a></li>

								</ul></li>
							<li><a href=""> <i class="fa fa-search"></i>
							</a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<li><a href=""> Account </a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> Dropdown <b class="caret"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="../pdf/2016GCEOExamTimetable.pdf" target="_blank">Time Table</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something</a></li>
									<li class="divider"></li>
									<li><a href="#">Separated link</a></li>
								</ul></li>
							<li><a href="logout.jsp"> Log out </a></li>
						</ul>
					</div>
				</div>
			</nav>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Examination</h4>
									<p class="category">Apply for the examinations</p>
								</div>
								<div class="content table-responsive table-full-width">
									<table class="table table-hover table-striped">
										<thead>
											<th>Year</th>
											<th>Semester</th>
											<th>Apply</th>
										</thead>
										<tbody>
											<tr>
												<td>
													<select class="" id="selYear">
													  <option>Year 1</option>
													  <option>Year 2</option>
													  <option>Year 3</option>
													  <option>Year 4</option>
													</select>
												</td>
												<td><select class="" id="selSemester">
													  <option>Semester 1</option>
													  <option>Semester 2</option>
													</select></td>
												<td>
													<button type="button" class="btn btn-info" id="apply"><a href="#login-box" class="login-window m5">Apply</a></button>	
												</td>
											</tr>
										</tbody>
									</table>

								</div> 
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Examination Table</h4>
									<p class="category">Here is your examination details</p>
								</div>
								<div class="content table-responsive table-full-width" ng-controller="examController" >
									<table class="table table-hover table-striped">
										<thead>
											<th>ID</th>
											<th>Name</th>
											<th>RegNo</th>
											<th>Year</th>
											<th>Semester</th>
											<th>Status</th>
										</thead>
										<tbody>
											<tr ng-repeat="e in exams">
												<td>{{ e.id }}</td>
												<td>{{ e.name }}</td>
												<td>{{ e.regNo }}</td>
												<td>{{ e.year }}</td>
												<td>{{ e.semester }}</td>
												<td>{{ e.status }}</td>
											</tr>									
										</tbody>
									</table>

								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="card card-plain"></div>
						</div>
					</div>
				</div>
			</div>

			<footer class="footer">
				<div class="container-fluid">
					<nav class="pull-left">
						<ul>
							<li><a href="../"> Home </a></li>
							<li><a href="#"> Company </a></li>
							<li><a href="#"> Portfolio </a></li>
							<li><a href="#"> Blog </a></li>
						</ul>
					</nav>
					<p class="copyright pull-right">
						&copy; 2017 <a href="#"><b>Gautam Kumar</b></a>, made for the SENSIPLE
					</p>
				</div>
			</footer>
		</div>
	</div>
	<!-- popup window -->
	<div id="login-box" class="login-popup">
		<a href="#" class="close"><img src="../close_pop.png"
			class="btn_close" title="Close Window" alt="Close" id="close"/></a>
		<form method="post" class="signin" action="" id="registerForm">		
			<fieldset class="textbox">
				<div ng-controller="studentController">
				<label class="username" > <span >Name</span> <input
					id="username" name="username" value="{{student[0].name}}" type="text" 
					autocomplete="on" placeholder="Username"></div>
				</label> <label class="username"> <span>Reg No</span> <input
					id="regNo" name="regNo" value="" type="text" placeholder="Reg No" >
				</label> <label class="username"> <span>Year</span> <input id="year"
					name="year" value="" type="text" placeholder="Year">
				</label> <label class="username"> <span>Semester</span> <input
					id="sem" name="sem" value="" type="text" placeholder="Semester">
				</label> <input type="submit" class="submit button" value="Register">
			</fieldset>
		</form>
	</div>
	<!-- <script type="text/javascript">
		Cufon.now();
	</script> -->
<script type="text/javascript">
$(document).ready(function() {
	$('a.login-window').click(function() {
		
		var regNo = document.getElementById("hide").value;
		var year = document.getElementById("selYear").value;
		var sem = document.getElementById("selSemester").value;
		
		document.getElementById("year").value=year;
		document.getElementById("sem").value=sem;
		document.getElementById("regNo").value=regNo;
		// Getting the variable's value from a link 
		var loginBox = $(this).attr('href');

		//Fade in the Popup and add close button
		$(loginBox).fadeIn(300);
		
		//Set the center alignment padding + border
		var popMargTop = ($(loginBox).height() + 24) / 2; 
		var popMargLeft = ($(loginBox).width() + 24) / 2; 
		
		$(loginBox).css({ 
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});
		
		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);
		
		return false;
	});
	
	// When clicking on the button close or the mask layer the popup closed
	$('a.close, #mask').live('click', function() { 
	  $('#mask , .login-popup').fadeOut(300 , function() {
		$('#mask').remove();  
	}); 
	return false;
	});
});
</script>
</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>

</html>
