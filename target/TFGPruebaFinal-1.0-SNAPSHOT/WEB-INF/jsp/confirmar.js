/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function confirmar()
{
	if(confirm('¿Estas seguro de eliminar este usuario?'))
		return true;
	else
		return false;
}

function ayuda(){
           alert('Si usted tiene alguna duda o sugerencia, contacte con: sistedestfg@gmail.com.');
       }
       
function ayudaNewUsuario(){
           alert('Si el usuario es externo, su ID es "EXT". En otro caso seguirá este patrón SxxxxxN (o F en caso de fundador)');
       }
       
       
