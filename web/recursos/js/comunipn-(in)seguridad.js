/*
    Librerias JS para ComunIPN
    Por Eder Ortega - multiaportes.com
*/

function obtenerHash(texto)
{
    try
    {
        var objetoHash = new jsSHA(texto, 'TEXT'); // Texto de entrada (text, hex, base-64)
        var salidaHash = objetoHash.getHash('SHA-1', 'HEX', 1); // Variante SHA (sha-1, sha-512, etc), salida (hex, base-64), rondas
        return salidaHash;
    }
    catch(x)
    {
        console.error('Error al generar el hash: '+x.message);
    }
}