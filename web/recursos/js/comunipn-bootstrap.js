/*
    Librerias JS para ComunIPN
    Por Eder Ortega - multiaportes.com
*/

function cargarTooltips()
{
    $('#contenidoxDD a').attr('data-toggle', 'tooltip');
    $('#contenidoxDD a').attr('data-placement', 'top');
    $('#contenidoxDD a').tooltip();
}
$(document).ready(cargarTooltips()); // Funcion de retrollamada 'callback'

function publicarPregunta()
{
    BootstrapDialog.show(
        {
            title: 'Publicar pregunta',
            message: $('<form role="form"><input type="text" maxlength="60" onblur="capitalizarPrimeraLetra(this)" class="form-control" name="titulo-pregunta" id="t-preg" placeholder="T&iacute;tulo"><br/><textarea maxlength="300" onblur="capitalizarPrimeraLetra(this)" class="form-control" name="detalles-pregunta" id="d-preg" placeholder="Detalles" style="resize: none;"></textarea><br/><span class="help-block">Recuerda que puedes usar <b>@menciones</b> y <b>#Hashtags</b> en cualquier momento.</span><br/><label class="radio-inline"><input type="radio" name="categoria-pregunta" id="c-preg" value="0"> F&iacute;sico-Matem&aacute;ticas</label><label class="radio-inline"><input type="radio" name="categoria-pregunta" id="c-preg" value="1"> M&eacute;dico-Biol&oacute;gicas</label><label class="radio-inline"><input type="radio" name="categoria-pregunta" id="c-preg" value="2"> Sociales</label><label class="radio-inline"><input type="radio" name="categoria-pregunta" id="c-preg" value="3"> Idiomas</label><label class="radio-inline"><input type="radio" name="categoria-pregunta" id="c-preg" value="4"> Offtopic</label></form>'),
            buttons: [
                {
                    label: 'Publicar',
                    cssClass: 'btn-primary',
                    action: function()
                    {
                        makePregunta();
                    }
                }
            ]
        }
    );
}