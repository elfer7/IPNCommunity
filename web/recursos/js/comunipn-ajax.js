/*
    Librerias JS para ComunIPN
    Por Eder Ortega - multiaportes.com
*/

function registrarUsuario(nm, ap, ml, usr, hashPs)
{
    $.post(
        "registrar",
        {
            nm: nm,
            ap: ap,
            ml: ml,
            usr: usr,
            hashPs: hashPs
        }
    ).done(
        function(respuesta)
        {
            if(respuesta === "OK")
                iniciarSesion();
            else
                alert('Error: el usuario ya existe');
        }
    );
}
function iniciarSesionUsuario(usr, hashPs)
{
    $.post(
        "login",
        {
            usr: usr,
            hashPs: hashPs
        }
    ).done(
        function(respuesta)
        {
            if(respuesta === "OK")
            {
                window.location.href = "categoria?c=0";
            }
            else
                alert('Error: usuario o contraseña incorrecto');
        }
    );
}
function publicarPreg(titulo, detalles, categoria)
{
    $.post(
        "nuevapregunta",
        {
            tt: titulo,
            dts: detalles,
            ctg: categoria
        }
    ).done(
        function()
        {
            alert('Pregunta publicada exitosamente');
        }
    );
}
function publicarResp(idP, cont)
{
    $.post(
        "nuevarespuesta",
        {
            id: idP,
            rep: cont
        }
    ).done(
        function()
        {
            
        }
    );
    $('#newReply').before('<div class="row respuesta"><div class="col-md-1"></div><div class="col-md-11"><div id="usuario-perfil-2" class="col-md-12">Respuesta</div><div class="col-md-12">'+cont+'</div></div></div>');
    $('#nuevaRespuesta').val('');
    $('#nuevaRespuesta').attr('disabled', 'true');
    $('#responderBtn').attr('disabled', 'true');
}
function votarComment(idP, numComment, tipo)
{
    $.post(
        "votarcomentario",
        {
            id: idP,
            kk: numComment,
            tipo: tipo
        }
    ).done(
        function()
        {
            
        }
    );
}
function editarPerfil(id, a, b, c, d, e, f)
{
    $.post(
        "guardar",
        {
            id: id,
            a: a,
            b: b,
            c: c,
            d: d,
            e: e,
            f: f
        }
    ).done(
        function()
        {
            alert('Cambios realizados');
        }
    );
}
function desactivarPerfil(id)
{
    $.post(
        "desactivar",
        {
            id: id
        }
    ).done(
        function()
        {
            alert('Perfil desactivado');
            location.href = "salir";
        }
    );
}
