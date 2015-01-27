package io.pivotal.gss.controller;

import java.util.HashMap;
import java.util.Map;

public class backTest {
	public boolean validateCmd(Map<String, String> session)
	{
		if(session.containsKey("api") &&  session.containsKey("username") && session.containsKey("passwd") 
				&& session.containsKey("org") && session.containsKey("space"))
		{
			return true;
		}
		return false;
	}
	
	//public String[] parseInput(HttpSession session, String[] arguments)
	public String[] parseInput(Map<String, String> session, String[] arguments)
	{
		String realArg[] = null;
		if(arguments != null)
		{
			//1)get real real arguments			
			int argCount = arguments.length;
			
			if(argCount == 1)
			{
				realArg = new String[1];
				realArg[0] = arguments[0];
				//set other para in login cmd
				if(session.get("api")== null)
				{
					//this is api string
					session.put("api", realArg[0]);
					realArg[0] = "https://api.run.pivotal.io";
				}
				else if(session.get("username")== null)
				{
					//this is username string
					session.put("username", realArg[0]);
					realArg[0] = "fwang@pivotal.io";
				}
				else if(session.get("passwd")== null)
				{
					//this is passwd string
					session.put("passwd", realArg[0]);
					realArg[0] = "wang123456";
				}
				else if(session.get("org")== null)
				{
					//this is org string
					session.put("org", realArg[0]);
					realArg[0] = "gss-apj";
				}
				else if(session.get("space")== null)
				{
					//this is space string
					session.put("space", realArg[0]);
					realArg[0] = "fwang";
				}
				return realArg;
				
			}
			else if(argCount > 1)
			{
				realArg = new String[argCount-1];				
			}
			else
			{
				//it is cf cmd with no parameter				
				return null;
			}
				
			for(int j=1;j<argCount;j++)
			{
				realArg[j-1] = arguments[j];
			}
			argCount = argCount-2;
			//2)now, we deal with cf login, cf targets, cf push and cf apps
			int i = 0;
			if(realArg[i].equals("login") == true)
			{				
				//1) get sessionid
				//2) get api,usrname,password,org,space
				//3) set information into session
				//4) generate actual cmd
				//String sId = session.getId();
				String sessionKey = null;
				String sessionContent = null;
				//String parameterIndexKey = "parameterIndex";
				//String parameterIndexContent = null;
				String cmdTypeKey = "cmdType";
				String cmdTypeContent = "login";
				//session.setAttribute("cmdType", cmdTypeContent);
				session.put(cmdTypeKey, cmdTypeContent);
				for(i=i+1;i<argCount;)
				{
					if(realArg[i].equals("-a"))
					{
						sessionKey = "api";
						sessionContent = realArg[i+1];
						//change the api with the working api
						realArg[i+1] = "https://api.run.pivotal.io";
					}
					else if(realArg[i].equals("-u"))
					{
						sessionKey = "username";
						sessionContent = realArg[i+1];
						realArg[i+1] = "fwang@pivotal.io";
					}
					else if(realArg[i].equals("-p"))
					{
						sessionKey = "passwd";
						sessionContent = realArg[i+1];
						realArg[i+1] = "wang123456";
					}
					else if(realArg[i].equals("-o"))
					{
						sessionKey = "org";
						sessionContent = realArg[i+1];
						realArg[i+1] = "gss-apj";
					}
					else if(realArg[i].equals("-s"))
					{
						sessionKey = "space";
						sessionContent = realArg[i+1];
						realArg[i+1] = "fwang";
					}
					//tunning i for interactive op
					//parameterIndexContent = String.valueOf((i+1)/2);
					//session.setAttribute(sessionKey, sessionContent);
					session.put(sessionKey, sessionContent);
					//session.setAttribute(parameterIndexKey, parameterIndexContent);
					i = i + 2;
				}
			}
			else if(realArg[i].equals("targets"))
			{	
				if(validateCmd(session) == true)
				{
					for(String key: session.keySet())
					{
						System.out.println(key + "  " + session.get(key));
					}
				}
				else
				{
					System.out.println("pls login first");
				}
			}
			else if(realArg[i].equals("push"))
			{
				if(validateCmd(session) == true)
				{
					for(String key: session.keySet())
					{
						System.out.println(key + "  " + session.get(key));
					}
				}
				else
				{
					System.out.println("pls login first");
				}				
			}
			else if(realArg[i].equals("apps"))
			{
				if(validateCmd(session) == true)
				{
					for(String key: session.keySet())
					{
						System.out.println(key + "  " + session.get(key));
					}
				}
				else
				{
					System.out.println("pls login first");
				}
			}
			else if(realArg[i].equals("help"))
			{
				if(validateCmd(session) == true)
				{
					for(String key: session.keySet())
					{
						System.out.println(key + "  " + session.get(key));
					}
				}
				else
				{
					System.out.println("pls login first");
				}
			}					
		}
		else
		{
			return null;
		}
		return realArg;		
	}
	
	public void testMain()
	{
		Map<String,String> session = new HashMap<String,String>();
		String acArgs[] = null;	
		
		
		//test case 1
		session.clear();
		String[] ar11 = {"cf","login","-a","https://api.run.pivotal.io","-u","fwang@pivotal.io","-p","wang123456","-o","org","-s","fwang"};
		acArgs = parseInput(session,ar11);
		
		//test case 2
		session.clear();
		String[] ar21 = {"cf", "login"};
		acArgs = parseInput(session,ar21);
		String[] ar22 = {"api.run.pivotal.io"};
		acArgs = parseInput(session,ar22);
		String[] ar23 = {"fwang@pivotal.io"};
		acArgs = parseInput(session,ar23);
		String[] ar24 = {"wang123456"};
		acArgs = parseInput(session,ar24);
		String[] ar25 = {"org"};
		acArgs = parseInput(session,ar25);
		String[] ar26 = {"fwang"};
		acArgs = parseInput(session,ar26);
		
		//test case 3
		session.clear();
		String[] ar31 = {"cf", "login","-p","wang123456"};
		acArgs = parseInput(session,ar31);
		String[] ar32 = {"api.run.pivotal.io"};
		acArgs = parseInput(session,ar32);
		String[] ar33 = {"fwang@pivotal.io"};
		acArgs = parseInput(session,ar33);		
		String[] ar34 = {"org"};
		acArgs = parseInput(session,ar34);
		String[] ar35 = {"fwang"};
		acArgs = parseInput(session,ar35);
		
		//test case 4
		session.clear();
		String[] ar41 = {"cf", "login","-p","wang123456","-s","fgwang"};
		acArgs = parseInput(session,ar41);
		String[] ar42 = {"api.run.pivotal.io"};
		acArgs = parseInput(session,ar42);
		String[] ar43 = {"fwang@pivotal.io"};
		acArgs = parseInput(session,ar43);		
		String[] ar44 = {"org"};
		acArgs = parseInput(session,ar44);	
		
		//test case 5
		session.clear();
		String[] ar51 = {"cf","login","-a","https://api.run.pivotal.io","-u","fwang@pivotal.io","-p","wang123456","-o","org","-s","fwang"};
		acArgs = parseInput(session,ar51);
		String[] ar52 = {"cf","targets"};
		acArgs = parseInput(session,ar52);
		
		//test case 6
		session.clear();
		String[] ar61 = {"cf","login","-a","https://api.run.pivotal.io","-u","fwang@pivotal.io","-o","org","-s","fwang"};
		acArgs = parseInput(session,ar61);
		String[] ar62 = {"cf","targets"};
		acArgs = parseInput(session,ar62);
		
		//test case 7
		session.clear();
		String[] ar71 = {"cf","login","-a","https://api.run.pivotal.io","-u","fwang@pivotal.io","-p","wang123456","-o","org","-s","fwang"};
		acArgs = parseInput(session,ar71);
		String[] ar72 = {"cf","push","project1"};
		acArgs = parseInput(session,ar72);
		
		//test case 8
		session.clear();
		String[] ar81 = {"cf","login","-a","https://api.run.pivotal.io","-u","fwang@pivotal.io","-p","wang123456","-o","org","-s","fwang"};
		acArgs = parseInput(session,ar81);
		String[] ar82 = {"cf","apps"};
		acArgs = parseInput(session,ar82);
		
		
//		for(String s:acArgs )
//		{
//			System.out.println(s);
//		}		
	}

}
