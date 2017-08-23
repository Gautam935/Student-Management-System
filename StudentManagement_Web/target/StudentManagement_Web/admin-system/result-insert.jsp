<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("name") == null) {
		response.sendRedirect("admin-login.html");
	}
	out.print("<input type='hidden' value='" + session.getAttribute("name") + "' id='hide'/>");
%>
<!doctype html>
<html lang="en" ng-app="myApp">
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Inserting results</title>

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
	var app = angular.module("myApp", []);

    app.controller('resultController' , function($scope , $http) {
		$http.get("http://localhost:8089/result/getResults?id=10").then(
			function(response) {
				$scope.results = response.data;
			}		
		);
		$http.get("http://localhost:8089/student/getAllStudentId").then(
			function(response) {
				$scope.student = response.data;
			}		
		);
		$http.get("http://localhost:8089/subjects/allSubjectId").then(
			function(response) {
				$scope.subject = response.data;
			}		
		);
		$scope.submit = function() {
			if($scope.resultValue == null) {
				$scope.resultValue = "ab"
			}
			
			var dataObject = {
				"stuId" : $scope.studentId,
				"subjectName" : $scope.subjectId,
				"result" : $scope.resultValue				
			};
			
			$http.post("http://localhost:8089/result/addResult",dataObject ).then(
				function() {
					alert("successfully added");
					location.reload();
				} , 
				function() {
					alert("successfully added");
				}
				
			);
		}
		$scope.deletefun = function(id) {
			$http({
			    method: 'DELETE',
			    url: 'http://localhost:8089/result/deleteResult?id='+id,
			    data: {
			    	id: id
			    },
			    headers: {
			        'Content-type': 'application/json;charset=utf-8'
			    }
			}).then(
				function() {
					var conf1 = confirm("notification successfully deleted, Do you need to reload the page?")
					if(conf1 == true) {		
						location.reload();
					}
					
				}, function() {
					alert("something went wrong");
				}
			);
		}
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
						ADMIN SITE </a>
				</div>

				<ul class="nav">
					<li><a href="dashboard.jsp"> <i class="pe-7s-graph"></i>
							<p>Student Feedback</p>
					</a></li>
					<li><a href="student.jsp"> <i class="pe-7s-user"></i>
							<p>Student</p>
					</a></li>
					<li ><a href="teacher.jsp"> <i
							class="pe-7s-note2"></i>
							<p>Teacher</p>
					</a></li>
					<li><a href="subject/subject-view.jsp"> <i class="pe-7s-news-paper"></i>
							<p>Subject</p>
					</a></li>
					<li><a href="notifications/notification-insert.jsp"> <i class="pe-7s-science"></i>
							<p>Notifications</p>
					</a></li>
					<!-- <li><a href="maps.html"> <i class="pe-7s-map-marker"></i>
							<p>Maps</p>
					</a></li> -->
					<li><a href="examRegistration.jsp"> <i class="pe-7s-bell"></i>
							<p>Approval</p>
					</a></li>
					<li class="active"><a href="result-insert.jsp"> <i class="pe-7s-bell"></i>
							<p>Results</p>
					</a></li>
					<li class="active-pro"><a
						href="https://www.sensiple.com/">
							<i class="pe-7s-rocket"></i>
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
							<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-globe"></i> <b
									class="caret"></b> <span class="notification">5</span>
							</a>
								<ul class="dropdown-menu" >
									<li ng-repeat="n in notification"><a href="#"></a></li>

								</ul></li> -->
							<li><a href=""> <i class="fa fa-search"></i>
							</a></li>
						</ul>

						<ul class="nav navbar-nav navbar-right">
							<!-- <li><a href=""> Account </a></li>
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
								</ul></li> -->
							<li><a href="logout.jsp"> Log out </a></li>
						</ul>
					</div>
				</div>
			</nav>
			<div class="content">
				<div class="container-fluid">
					<div class="row" ng-controller="resultController">
						<div class="col-md-12">
							<div class="card">
								<div class="header">
									<h4 class="title">Result</h4>
									<p class="category">Add Results</p>
								</div>
								<div class="content table-responsive table-full-width">
									<table class="table table-hover table-striped">
										<thead>
											<th>Student ID</th>
											<th>Subject Name</th>
											<th>Result</th>
											<th>Action</th>
										</thead>
										<tbody>
											<tr>
												<td>
													<select class="" id="studentId" ng-model="studentId">
													  <option ng-repeat="stu in student">{{ stu }}</option>
													  
													</select>
												</td>
												<td><select class="" id="subjectId" ng-model="subjectId">
													  <option ng-repeat="sub in subject">{{ sub }}</option>
													</select></td>
													<!-- <td><input type="text" class="form-control" ng-model = "resultValue" id="resultValue"></td> -->
												<td><select class="" id="subjectId" ng-model="resultValue">
													  <option>A+</option>
													  <option>A</option>
													  <option>A-</option>
													  <option>B+</option>
													  <option>B</option>
													  <option>B-</option>
													  <option>C+</option>
													  <option>C</option>
													  <option>C-</option>
													  <option>D+</option>
													  <option>D</option>
													  <option>E</option>
													  <option>ab</option>
													</select></td>
												<td>
													<button type="button" class="btn btn-info" ng-click="submit()">Apply</button>	
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
								<div class="content table-responsive table-full-width" >
									<table class="table table-hover table-striped">
										<thead>
											<th>ID</th>
											<th>Student Id</th>
											<th>Subject Name</th>
											<th>Result</th>
											<th>Action</th>
										</thead>
										<tbody>
											<tr ng-repeat="r in results">
												<td>{{ r.id }}</td>
												<td>{{ r.stuId }}</td>
												<td>{{ r.subjectName }}</td>
												<td>{{ r.result }}</td>
												<td ><button type="button" rel="tooltip" title="Remove"
														id="delete" data="{{ r.id }}"
														class="btn btn-danger btn-simple btn-xs" ng-click="deletefun(r.id)">
														<i class="fa fa-times"></i>
													</button></td>
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
