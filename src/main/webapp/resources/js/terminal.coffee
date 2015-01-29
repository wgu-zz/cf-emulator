do @myTerminal = ->

  @basesettings = {
    prompt: 'you@cf-emulator:~$ ',
    greetings: """
               Welcome to the interactive cf emulator
              """
  }

  ###
    Callback definitions. These can be overridden by functions anywhere else
  ###

  @preventDefaultCallback = false

  @immediateCallback = (command) ->
    console.debug("immediate callback from #{command}")
    return

  @finishedCallback = (command) ->
    console.debug("finished callback from #{command}")
    return

  @intermediateResults = (string) ->
    console.debug("sent #{string}")
    return

  ###
    Base interpreter
  ###

  @interpreter = (input, term) ->
    inputs = input.split(" ")
    command = inputs[0]
    
    if input.indexOf(">") > 0
      cf(term, input)

    else if command is "cf"
      cf(term, input)

    else if command is "help"
      term.echo help

    else if command is "ls"
      term.echo "This is an emulator, not a shell. Try following the instructions."
      
    else if command
      term.echo "#{inputs[0]}: command not found"

    immediateCallback(inputs)

  ###  =======================================
    Common utils
  =======================================  ###

  String.prototype.beginsWith = (string) ->
    ###
    Check if 'this' string starts with the inputstring.
    ###
    return(this.indexOf(string) is 0)

  Array.prototype.containsAllOfThese = (inputArr) ->
    ###
    This function compares all of the elements in the inputArr
    and checks them one by one if they exist in 'this'. When it
    finds an element to not exist, it returns false.
    ###
    me = this
    valid = false

    if inputArr
      valid = inputArr.every( (value) ->
        if me.indexOf(value) == -1
          return false
        else
          return true
      )
    return valid


  Array.prototype.containsAllOfTheseParts = (inputArr) ->
    ###
    This function is like containsAllofThese, but also matches partial strings.
    ###

    me = this
    if inputArr
      valid = inputArr.every( (value) ->
        for item in me
          if item.match(value)
            return true

        return false
      )
    return valid


  parseInput = (inputs) ->
    command = inputs[1]
    switches = []
    switchArg = false
    switchArgs = []
    imagename = ""
    commands = []
    j = 0

    # parse args
    for input in inputs
      if input.startsWith('-') and imagename == ""
        switches.push(input)
        if switches.length > 0
          if not ['-i', '-t', '-d'].containsAllOfThese([input])
            switchArg = true
      else if switchArg == true
        # reset switchArg
        switchArg = false
        switchArgs.push(input)
      else if j > 1 and imagename == ""
        # match wrong names
        imagename = input
      else if imagename != ""
        commands.push (input)
      else
        # nothing?
      j++

    parsed_input = {
      'switches': switches.sortBy(),
      'switchArgs': switchArgs,
      'imageName': imagename,
      'commands': commands,
    }
    return parsed_input


  util_slow_lines = (term, paragraph, keyword, finishedCallback) ->

    if keyword
      lines = paragraph(keyword).split("\n")
    else
      lines = paragraph.split("\n")

    term.pause()
    i = 0
    # function calls itself after timeout is done, untill
    # all lines are finished
    foo = (lines) ->
      self.setTimeout ( ->
        if lines[i]
          term.echo (lines[i])
          i++
          foo(lines)
        else
          term.resume()
          finishedCallback()
      ), 1000

    foo(lines)


  wait = (term, time, dots) ->
    term.echo "starting to wait"
    interval_id = self.setInterval ( -> dots ? term.insert '.'), 500

    self.setTimeout ( ->
      self.clearInterval(interval_id)
      output = term.get_command()
      term.echo output
      term.echo "done "
    ), time

  ###
    cf program
  ###

  cf = (term, input) ->

    echo = term.echo
    insert = term.insert
    callback = () -> @finishedCallback(input)
    
    # execute actual cf commands and get output here
    stompClient.send '/app/run', {}, JSON.stringify input
    return

return this


