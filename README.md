# cf-emulator
A GSS Hackathon project<br />
Team GAP

This is a command line emulator for the Pivotal Cloud Foundry cf CLI, as well as a get-started interactive tutorial for playing with CloudFoundry. It itself is hosted on Pivotal Web Services (Pivotal backed CloudFoundry). Visit the site at http://cf-emulator.cfapps.io.<br />

You can run most of the real cf commands in the emulator as it is actually sending the command to the backend and replacing the i/o so you will see only session based/specific information.<br />
It is opening a websocket connection every time you open the page to allow interactive message exchanging between the terminal emulator and the backend server(PWS uses tomcat 8 for now for Java web applications).  

Project Tracker:<br />
https://www.pivotaltracker.com/n/projects/1249134




