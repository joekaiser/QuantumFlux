package jotato.quantumflux;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;

public class Logger
{

	public static void log(Level level, String format, Object... data)
	{

		FMLLog.log(level, "[@QuantumFlux] " + format, data);
	}

	public static void fatal(String format, Object... data)
	{
		log(Level.FATAL, format, data);
	}

	public static void error(String format, Object... data)
	{
		log(Level.ERROR, format, data);
	}

	public static void warning(String format, Object... data)
	{
		log(Level.WARN, format, data);
	}

	public static void info(String format, Object... data)
	{
		log(Level.INFO, format, data);
	}


	public static void devLog(String format, Object... data)
	{
		if (ConfigMan.isDebug)
		{
			log(Level.INFO, format, data);
		}
	}

}
