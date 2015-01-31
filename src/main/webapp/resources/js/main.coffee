###
  Array of step objects
###

q = []
q.push ({
html: """
      <h3>Getting started</h3>
      <p>cf CLI v6 installs with a simple point-and-click, and you no longer need to install Ruby on your system first 
      (or ever). You can use new binaries or new native installers. See 
      <a href="http://docs.run.pivotal.io/devguide/installcf/install-go-cli.html">Install cf CLI Version 6</a>. 
      </br></br> For this emulator, simply type cf commands without any installation! Now try <code>cf help</code>.
      </br></br>Please note this command line emulator includes subset of the cf command set. Some commands may not work.
      </p>
      """
})

q.push ({
html: """
      <h3>Login and Play!</h3>
      <p>In addition to your username and 
      password, you can provide a target API endpoint, organization, and space. If not specified on the command line, 
      the cf CLI prompts for:</br></br><li><strong>API endpoint</strong>: This is <code>api.run.pivotal.io</code>
	  <li><strong>Username</strong>: Your username.</li>
	  <li><strong>Password</strong>: Your password.</li>
	  <li><strong>Org</strong>: The organization where you want to deploy your application.</li>
	  <li><strong>Space</strong>: The space in the organization where you want to deploy your application.</li></p>
	  <p>If you have only one organization and one space, you can omit them because <code>cf login</code> targets them automatically. 
	  </br></br>Usage:</p>
	  <pre id="pre1" class="terminal1">
	  cf login [-a API_URL] [-u USERNAME] [-p PASSWORD] [-o ORG] [-s SPACE]
	  </pre></p>
	  """
})

q.push ({
html: """
      <h3>Get Target Info</h3>
      <p>The <code>target</code> command in the cf CLI v6 is used to set or view the targeted org or space:</br></br>
	  <li><strong>Usage</strong>:</li></p>
	  <pre id="pre1" class="terminal1">
	  cf target [-o ORG] [-s SPACE]
	  </pre>
      """
})

q.push ({
html: """
      <h3>Get Something Real!</h3>
	 <p>The <code>push</code> command is used to deploy your app to CF platforms.</br>Try use the command to deploy and 
	 start your own web application now. Don't worry if you have no current project. The emulator has prepared a simple sample.</p>
	 <ul>
	 <li><code>APP</code>, the name of the application to push, is the only required argument, and the only argument that has no flag. Even <code>APP</code> can be omitted when you provide the application name in a manifest.</li>
	 <li>Many command line options are now one character long. For example, <code>-n</code> is now the flag for hostname or subdomain, replacing <code>--host</code>.</li>
	 <li>There is no longer an interactive mode.</li>
	 <li>You no longer create manifests interactively. See <a href="/devguide/deploy-apps/manifest.html">Deploying with Application Manifests</a>.</li>
	 <li>You no longer create services with push interactively or in a manifest. See <a href="#user-provided">User-Provided Services</a> to learn about new commands for creating services.</li>
	 <li>The <code>-m</code> (memory limit) option now requires a unit of measurement: <code>M</code>,<code>MB</code>,<code>G</code>, or <code>GB</code>, in upper case or lower case.</li>
	 </ul>
	 <p>The cf CLI v6 has expanded capabilities in the form of four new options.</p>
	 <ul>
	 <li><code>-t</code> (timeout) allows you to give your application more time to start, up to 180 seconds.</li>
	 <li><code>--no-manifest</code> forces the cf CLI to ignore any existing manifest.</li>
	 <li><code>--no-hostname</code> makes it possible to specify a route with a domain but no hostname.</li>
	 <li><code>--no-route</code> is suitable for applications which process data while running in the background. These applications, sometimes called &ldquo;workers&rdquo; are bound only to services and should not have routes.</li>
	 </ul>
	 <p>Usage:</p>
	 <pre id="pre1" class="terminal1">
	 cf push APP [-b URL] [-c COMMAND] [-d DOMAIN] [-i NUM_INSTANCES] [-m MEMORY] /
	 [-n HOST] [-p PATH] [-s STACK] [--no-hostname] [--no-route] [--no-start]
	 </pre>
	 <p>Optional arguments include:</p>
	 <ul>
	 <li><code>-b</code> &mdash; Custom buildpack URL, for example, <a href="https://github.com/heroku/heroku-buildpack-play.git">https:// github.com/heroku/heroku-buildpack-play.git</a>
																																		// or
																																		// <a
																																		// href="https://github.com/heroku/heroku-buildpack-play.git#stable">https://github.com/heroku/heroku-buildpack-play.git#stable</a>
																																		// to
																																		// select
																																		// <code>stable</code>
																																		// branch</li>
	 <li><code>-c</code> &mdash; Start command for the application.</li>
	 <li><code>-d</code> &mdash; Domain, for example, example.com.</li>
	 <li><code>-f</code> &mdash; replaces <code>--manifest</code></li>
	 <li><code>-i</code> &mdash; Number of instances of the application to run.</li>
	 <li><code>-m</code> &mdash; Memory limit, for example, 256, 1G, 1024M, and so on.</li>
	 <li><code>-n</code> &mdash; Hostname, for example, <code>my-subdomain</code>.</li>
	 <li><code>-p</code> &mdash; Path to application directory or archive.</li>
	 <li><code>-s</code> &mdash; Stack to use.</li>
	 <li><code>-t</code> &mdash; Timeout to start in seconds.</li>
	 <li><code>--no-hostname</code> &mdash; Map the root domain to this application (NEW).</li>
	 <li><code>--no-manifest</code> &mdash; Ignore manifests if they exist.</li>
	 <li><code>--no-route</code> &mdash; Do not map a route to this application (NEW).</li>
	 <li><code>--no-start</code> &mdash; Do not start the application after pushing.</li>
	 </ul>
	 <p class='note'><strong>Note</strong>: The `&ndash;no-route` option also removes existing routes from previous pushes of this app.</p>
      """
})

q.push ({
html: """
      <h3>Awesome!</h3>
      <p>Now that you deployed your application to CloudFoundry, use the apps to check the information and status of the
      apps.</br></br>The <code>apps</code> command in the cf CLI v6 is used to list all apps in the target space:</br></br>
	  <li><strong>Usage</strong>:</li></p>
	  <pre id="pre1" class="terminal1">cf apps
	  </pre>
      """
})

# the index arr
questions = []

###
  Register the terminal
###

@webterm = $('#terminal').terminal(interpreter, basesettings)

EVENT_TYPES =
  none: "none"
  start: "start"
  command: "command"
  next: "next"
  peek: "peek"
  complete: "complete"



###
  Sending events to the server
###

logEvent = (data) ->
    #ajax_load = "loading......";
    loadUrl = "/tutorial/api/";
    callback = (responseText) -> $("#ajax").html(responseText)

    if not data then data = {type: EVENT_TYPES.none}
    data.question = current_question

    #$("#ajax").html(ajax_load);
    $.post(loadUrl, data, callback, "html")



###
  Event handlers
###


## next
$('#buttonNext').click (e) ->

  # disable the button to prevent spacebar to hit it when typing in the terminal
  this.setAttribute('disabled','disabled')
  console.log(e)
  next()

## Stop mousewheel on left side, and manually move it.
$('#leftside').bind('mousewheel',
  (event, delta, deltaX, deltaY) ->
    this.scrollTop += deltaY * -30
    event.preventDefault()
  )

## fullsize
$('#fullSizeOpen').click ->
  goFullScreen()

@goFullScreen = () ->
  console.debug("going to fullsize mode")
  $('.togglesize').removeClass('startsize').addClass('fullsize')

  $('.hide-when-small').css({ display: 'inherit' })
  $('.hide-when-full').css({ display: 'none' })

  next(0)

  webterm.resize()

  # send the next event after a short timeout, so it doesn't come at the same time as the next() event
  # in the beginning. Othewise two sessions will appear to have been started.
  # This will make the order to appear wrong, but that's not much of an issue.

  setTimeout( () ->
    logEvent( { type: EVENT_TYPES.start } )
  , 3000)


## leave fullsize
$('#fullSizeClose').click ->
  leaveFullSizeMode()

@leaveFullSizeMode = () ->
  console.debug "leaving full-size mode"

  $('.togglesize').removeClass('fullsize').addClass('startsize')

  $('.hide-when-small').css({ display: 'none' })
  $('.hide-when-full').css({ display: 'inherit' })

  webterm.resize()

###
  Navigation amongst the steps
###


current_question = 0
next = (which) ->
  # before increment clear style from previous question progress indicator
  $('#marker-' + current_question).addClass("complete").removeClass("active")

  if not which and which != 0
    current_question++
  else
    current_question = which

  questions[current_question]()
  results.clear()
  @webterm.focus()

  if not $('#commandShownText').hasClass('hidden')
    $('#commandShownText').addClass("hidden")
    $('#commandHiddenText').removeClass("hidden").show()

  # enable history navigation
  history.pushState({}, "", "#" + current_question);
  data = { 'type': EVENT_TYPES.next }
  logEvent(data)

  # change the progress indicator
  $('#marker-' + current_question).removeClass("complete").addClass("active")

  $('#question-number').find('text').get(0).textContent = current_question

  return

results = {
  set: (htmlText, intermediate) ->
    if intermediate
      console.debug "intermediate text received"
      $('#results').addClass('intermediate')

    window.setTimeout ( () ->
      $('#resulttext').html(htmlText)
      $('#results').fadeIn()
    ), 300

  clear: ->
    $('#resulttext').html("")
    $('#results').fadeOut('slow')
}



###
  Transform step objects into functions
###

buildfunction = (q) ->
  _q = q
  return ->
    console.debug("function called")

    $('#instructions').hide().fadeIn()
    $('#instructions .text').html(_q.html)

    window.immediateCallback = (input, stop) ->
      if stop == true # prevent the next event from happening
        doNotExecute = true
      else
        doNotExecute = false

      if doNotExecute != true
        console.log (input)

        data = { 'type': EVENT_TYPES.command, 'command': input.join(' '), 'result': 'fail' }

        # call function to submit data
        logEvent(data)
      return

    window.intermediateResults = (input) ->
      if _q.intermediateresults
        results.set(_q.intermediateresults[input](), intermediate=true)
    return


statusMarker = $('#progress-marker-0')
progressIndicator = $('#progress-indicator')

drawStatusMarker = (i) ->
  if i == 0
    marker = statusMarker
  else
    marker = statusMarker.clone()
    marker.appendTo(progressIndicator)

  marker.attr("id", "marker-" + i)
  marker.find('text').get(0).textContent = i
  marker.click( -> next(i) )


questionNumber = 0
for question in q
  f = buildfunction(question)
  questions.push(f)
  drawStatusMarker(questionNumber)
  questionNumber++


###
  Initialization of program
###

#load the first question, or if the url hash is set, use that
if (window.location.hash)
  try
    currentquestion = window.location.hash.split('#')[1].toNumber()
#    questions[currentquestion]()
#    current_question = currentquestion
    next(currentquestion)

  catch err
    questions[0]()
else
  questions[0]()

$('#results').hide()

String.prototype.endsWith = (suffix) ->
  this.indexOf(suffix, length - suffix.length) != -1

mark_index = 0
socket = new SockJS '/socket:4443'
@stompClient = Stomp.over socket
stompClient.connect {}, (frame) ->
  stompClient.subscribe '/broker/out', (output) ->
    message = JSON.parse output.body
    if message == "done!"
      next mark_index++
    else if message.endsWith "> "
      this.webterm.insert message
    else
      this.webterm.echo message

