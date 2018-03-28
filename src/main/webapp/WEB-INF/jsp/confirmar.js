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