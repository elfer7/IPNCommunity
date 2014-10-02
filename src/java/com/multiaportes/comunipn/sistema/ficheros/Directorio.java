package com.multiaportes.comunipn.sistema.ficheros;

import java.io.File;

public class Directorio
{
	private static Directorio dir = null;
	private static boolean creado;

	private Directorio()
	{
            creado = false;
	}

	public static boolean crearDirectorio(String ruta, long id) // ID usuario o pregunta
	{
            if(dir == null)
                    dir = new Directorio();
            return creado = (new File(ruta+Long.toString(id))).mkdirs();
	}
}