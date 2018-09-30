package utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Map;

public class LogUtils {
	
	private static Logger tempLogger = Logger.getLogger("tempLog");

	private static Logger saveLogger = Logger.getLogger("saveLog");
	
	private static Logger errorLogger = Logger.getLogger("errorLog");	

	public static void save(String message,Map<String,String> map) {
		if (StringUtils.isBlank(message)) {
			return;
		}
		String json = JsonUtils.getJsonStringFromMap(map);
		if (StringUtils.isBlank(json)) {
			json = "map_convert_error";
		}
		save(message+";"+json);
	}

	public static void save(String message)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");			
		saveLogger.info(classAndMethod.append(message));
	}	
	
	public static void save(String message, Throwable t)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");			
		saveLogger.info(classAndMethod.append(message), t);
	}
	
	public static void error(String message)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");		
		errorLogger.info(classAndMethod.append(message));
	}
	
	public static void error(String message, Throwable t)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");		
		errorLogger.info(classAndMethod.append(message), t);
	}

	public static void temp(String message)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");
		tempLogger.info(classAndMethod.append(message));
	}
	
	public static void temp(String message, Throwable t)
	{
		StringBuilder classAndMethod = getClassAndMethodName();
		classAndMethod.append(" ");		
		tempLogger.info(classAndMethod.append(message), t);
	}
	
	public static StringBuilder getClassAndMethodName()
	{
		StringBuilder classAndMethod = new StringBuilder("");
		StackTraceElement[] stacks = (new Throwable()).getStackTrace();
		if ( stacks == null || stacks.length < 3 )
		{
			return classAndMethod;
		}
		
		StackTraceElement stack = stacks[2];
		if ( stack != null )
		{
    		String className = stack.getClassName();
    		int lastPos = className.lastIndexOf(".");
    		if ( lastPos != -1 )
    		{
    		    classAndMethod.append(className.substring(lastPos));
    		    classAndMethod.append("-");
    		}
    		classAndMethod.append(stack.getMethodName());
		}

		return classAndMethod;
	}
}
