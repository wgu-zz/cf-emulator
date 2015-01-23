<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cf-emulator</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/lib/css/jquery.terminal.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css/cf-style.css" />" />
</head>
<body>

	<div id="overlay" class="tutorial togglesize startsize">
		<div id="main" class="">

			<div id="tutorialTop" class="togglesize hide-when-small">
				<img src="<c:url value="/resources/img/pcf-cloud-green.png" />"
					alt="cf emulator">
				<h1>Emulator</h1>

				<a id="fullSizeClose" href="#" class="closeButton"><img
					src="<c:url value="/resources/img/fullscreen_exit_32x32.png" />"
					title="close fullscreen"> </a>

				<div id="progress-indicator">
					<span id="progress-marker-0" class="progress-marker"> <svg
							xmlns="http://www.w3.org/2000/svg" version="1.1">
                    <circle cx="50%" cy="50%" r="50%"></circle>
                    <text x="50%" dy="50%" text-anchor="middle"
								dominant-baseline="central" fill="white">i</text>
                    </svg>
					</span>
				</div>
			</div>

			<div id="leftside" class="left togglesize hide-when-small">
				<div id="instructions">
					<div class="circle info-circle">
						<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
							id="question-number">
                        <circle cx="50%" cy="50%" r="50%"
								stroke-width="2"></circle>
                        <text x="50%" y="50%"
								dominant-baseline="central" text-anchor="middle" fill="white">1</text>
                    </svg>
					</div>
					<div class="text"></div>
				</div>

				<div id="ajax" class=""></div>
			</div>
			<!-- end left -->

			<div id="starttext" class="togglesize startsize hide-when-full">
				<h4>Learn the first steps of using cf CLI, such as:</h4>
				<ul>
					<li>Printing help</li>
					<li>Login to a CF API endpoint</li>
					<li>Pushing an application</li>
				</ul>

				<div style="text-align: center">
					<a id="fullSizeOpen" href="#"
						class="btn btn-primary primary-action-button">Start!</a>
				</div>
			</div>

			<div id="rightside">
				<div id="terminal-extenstion" class="right togglesize smallsize"></div>
				<div id="terminal" class="right togglesize smallsize"></div>

				<div id="results" class="results togglesize">
					<svg xmlns="http://www.w3.org/2000/svg" version="1.1"
						style="width: 40px; height: 40px; position: relative; float: left; top: 5; left: 5; z-index: 1;">
                    <circle cx="50%" cy="50%" r="50%" stroke-width="2"></circle>
                </svg>

					<div id="resulttext" class="text"></div>
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="<c:url value="/resources/lib/js/jquery-1.11.2.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/lib/js/jquery.mousewheel.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/lib/js/jquery.terminal-0.8.8.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/lib/js/sugar-1.3.9.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/terminal.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/main.js" />"></script>

	<!-- temporary solution to start in fullscreen -->
	<script type="text/javascript">
		$('document').ready(function() {
			goFullScreen();
		})
	</script>

</body>
</html>