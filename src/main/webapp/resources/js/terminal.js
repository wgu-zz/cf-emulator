// Generated by CoffeeScript 1.8.0
(function() {
  (this.myTerminal = function() {
    var cf, parseInput, util_slow_lines, wait;
    this.basesettings = {
      prompt: 'you@cf-emulator:~$ ',
      greetings: "Welcome to the interactive cf emulator"
    };

    /*
      Callback definitions. These can be overridden by functions anywhere else
     */
    this.preventDefaultCallback = false;
    this.immediateCallback = function(command) {
      console.debug("immediate callback from " + command);
    };
    this.finishedCallback = function(command) {
      console.debug("finished callback from " + command);
    };
    this.intermediateResults = function(string) {
      console.debug("sent " + string);
    };

    /*
      Base interpreter
     */
    this.interpreter = function(input, term) {
      var command, inputs;
      inputs = input.split(" ");
      command = inputs[0];
      if (input.indexOf(">") > 0) {
        cf(term, input);
      } else if (command === "cf") {
        cf(term, input);
      } else if (command === "help") {
        term.echo(help);
      } else if (command === "ls") {
        term.echo("This is an emulator, not a shell. Try following the instructions.");
      } else if (command) {
        term.echo("" + inputs[0] + ": command not found");
      }
      return immediateCallback(inputs);
    };

    /*  =======================================
      Common utils
    =======================================
     */
    String.prototype.beginsWith = function(string) {

      /*
      Check if 'this' string starts with the inputstring.
       */
      return this.indexOf(string) === 0;
    };
    Array.prototype.containsAllOfThese = function(inputArr) {

      /*
      This function compares all of the elements in the inputArr
      and checks them one by one if they exist in 'this'. When it
      finds an element to not exist, it returns false.
       */
      var me, valid;
      me = this;
      valid = false;
      if (inputArr) {
        valid = inputArr.every(function(value) {
          if (me.indexOf(value) === -1) {
            return false;
          } else {
            return true;
          }
        });
      }
      return valid;
    };
    Array.prototype.containsAllOfTheseParts = function(inputArr) {

      /*
      This function is like containsAllofThese, but also matches partial strings.
       */
      var me, valid;
      me = this;
      if (inputArr) {
        valid = inputArr.every(function(value) {
          var item, _i, _len;
          for (_i = 0, _len = me.length; _i < _len; _i++) {
            item = me[_i];
            if (item.match(value)) {
              return true;
            }
          }
          return false;
        });
      }
      return valid;
    };
    parseInput = function(inputs) {
      var command, commands, imagename, input, j, parsed_input, switchArg, switchArgs, switches, _i, _len;
      command = inputs[1];
      switches = [];
      switchArg = false;
      switchArgs = [];
      imagename = "";
      commands = [];
      j = 0;
      for (_i = 0, _len = inputs.length; _i < _len; _i++) {
        input = inputs[_i];
        if (input.startsWith('-') && imagename === "") {
          switches.push(input);
          if (switches.length > 0) {
            if (!['-i', '-t', '-d'].containsAllOfThese([input])) {
              switchArg = true;
            }
          }
        } else if (switchArg === true) {
          switchArg = false;
          switchArgs.push(input);
        } else if (j > 1 && imagename === "") {
          imagename = input;
        } else if (imagename !== "") {
          commands.push(input);
        } else {

        }
        j++;
      }
      parsed_input = {
        'switches': switches.sortBy(),
        'switchArgs': switchArgs,
        'imageName': imagename,
        'commands': commands
      };
      return parsed_input;
    };
    util_slow_lines = function(term, paragraph, keyword, finishedCallback) {
      var foo, i, lines;
      if (keyword) {
        lines = paragraph(keyword).split("\n");
      } else {
        lines = paragraph.split("\n");
      }
      term.pause();
      i = 0;
      foo = function(lines) {
        return self.setTimeout((function() {
          if (lines[i]) {
            term.echo(lines[i]);
            i++;
            return foo(lines);
          } else {
            term.resume();
            return finishedCallback();
          }
        }), 1000);
      };
      return foo(lines);
    };
    wait = function(term, time, dots) {
      var interval_id;
      term.echo("starting to wait");
      interval_id = self.setInterval((function() {
        return dots != null ? dots : term.insert('.');
      }), 500);
      return self.setTimeout((function() {
        var output;
        self.clearInterval(interval_id);
        output = term.get_command();
        term.echo(output);
        return term.echo("done ");
      }), time);
    };

    /*
      cf program
     */
    return cf = function(term, input) {
      var callback, echo, insert;
      echo = term.echo;
      insert = term.insert;
      callback = function() {
        return this.finishedCallback(input);
      };
      stompClient.send('/app/run', {}, JSON.stringify(input));
    };
  })();

  return this;

}).call(this);
