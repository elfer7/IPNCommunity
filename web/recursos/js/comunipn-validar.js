/*
    Librerias JS para ComunIPN
    Por Eder Ortega - multiaportes.com
*/

// Registro
function registroValidar(input, tipo)
{
    if(tipo == 'nombre' || tipo == 'apellido')
    {
        if(!input.value.match(/^[a-z]+$/i) || input.value.length < 5)
        {
            alert('Datos no validos');
            input.value = '';
        }
    }
    else if(tipo == 'email')
    {
        validarMail(input);
        // comprobar si el correo existe con AJAX();
    }
    else if(tipo == 'usuario')
    {
        validarUsuario(input);
        // comprobar si el correo existe con AJAX();
    }
    else if(tipo == 'clave')
    {
        validarPassword(input);
    }
}
function validarMail(input)
{
    if(input.value != '')
    {
        var arroba = false;
        var p = 0;
        input.value = input.value.replace(/\s/g, ''); //Regex que reemplaza espacios, '[g]lobal' no se detiene a la primera coincidencia
        while(p < input.value.length && input.value.charAt(0) != '@')
        {
            if(input.value.charAt(p) == '@')
            {
                arroba = true;
                break;
            }
            else
                p++;
        }
        if(arroba)
        {
            if(input.value.substring(p) == '@multiaportes.com' || input.value.substring(p) == '@outlook.com' || input.value.substring(p) == '@live.com' || input.value.substring(p) == '@gmail.com' || input.value.substring(p) == '@yahoo.com' || input.value.substring(p) == '@ipn.mx' || input.value.substring(p) == '@hotmail.com')
            {
                input.disabled = true;
            }
            else
            {
                alert('No es una direccion de correo reconocida');
                input.value = '';
            }
        }
        else
        {
            alert('Introduce una direccion de correo valida');
            input.value = '';
        }
    }
}
function validarUsuario(input)
{
    if(input.value != '')
    {
        if(input.value.length > 4 && input.value.match(/^[a-z0-9]+$/i)) // Regex: '^, $' inicio, fin; '+' una o mas veces; 'i' case no sensitive
        {
            
        }
        else
        {
            alert('El usuario debe tener al menos 5 caracteres alfanumericos');
            input.value = '';
        }
    }
}
function validarPassword(input)
{
    if(input.value != '')
    {
        if(input.value.length < 5)
        {
            alert('La contraseña debe tener minimo 5 caracteres');
            input.value = '';
        }
        else
            input.disabled = true;
    }
}
function makeAlta()
{
    if(document.getElementById('nm').value == '' || document.getElementById('ap').value == '' || document.getElementById('ml').value == '' || document.getElementById('usr').value == '' || document.getElementById('pswd').value == '')
        alert('No se han rellenado todos los campos');
    else
    {
        document.getElementById('hashPs').value = obtenerHash(document.getElementById('pswd').value);
        document.getElementById('pswd').value = '';
        registrarUsuario($('#nm').val(), $('#ap').val(), $('#ml').val(), $('#usr').val(), $('#hashPs').val());
    }
}
function makeLogin()
{
    if(document.getElementById('us-lg').value == '' || document.getElementById('pw-lg').value == '')
        alert('No hay datos suficientes');
    else
    {
        document.getElementById('pw-lg-h').value = obtenerHash(document.getElementById('pw-lg').value);
        document.getElementById('pw-lg').value = '';
        iniciarSesionUsuario($('#us-lg').val(), $('#pw-lg-h').val());
    }
}
function parsearDatos(caja) // Parsear URLs, Hashtags y Menciones
{
    var tmp = $(caja).val();
    tmp = tmp.replace(/((http|https):\/\/[a-z0-9\/\?\.\-_&=]+)/gi, '<a class="urlExterna" href="$1" rel="nofollow" target="_blank">$1</a>');
    tmp = tmp.replace(/#([a-z0-9]+)/gi, '<a class="hashtag" href="tag?h=$1">#$1</a>');
    tmp = tmp.replace(/@([a-z0-9]+)/gi, '<a class="mencion" href="inicio">@$1</a>');
    return tmp;
}
function makePregunta()
{
    if(document.getElementById('t-preg').value == '' || document.getElementById('d-preg').value == '' || $('input[name=categoria-pregunta]:checked').length <= 0)
        alert('Debes rellenar todos los campos');
    else
    {
        publicarPreg(document.getElementById('t-preg').value, parsearDatos('#d-preg'), $('input[name=categoria-pregunta]:checked').val());
        BootstrapDialog.closeAll();
    }
}
function makeComentario(idPreg)
{
    if(document.getElementById('nuevaRespuesta').value == '')
        alert('No puedes enviar una respuesta vacía');
    else
        publicarResp(idPreg, parsearDatos('#nuevaRespuesta'));
}
function capitalizarPrimeraLetra(caja)
{
    caja.value = caja.value.substr(0,1).toUpperCase() + caja.value.substr(1);
}