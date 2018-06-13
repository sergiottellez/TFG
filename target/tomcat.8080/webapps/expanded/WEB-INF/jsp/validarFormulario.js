/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarNIF(dni){
  var sizeDni = 9;
  var lockup = 'TRWAGMYFPDXBNJZSQVHLCKE';
  var valueDni=dni.substr(0,dni.length-1);
  var letra=dni.substr(dni.length-1,1).toUpperCase();

  if(dni.length==sizeDni && lockup.charAt(valueDni % 23)==letra){
    return true;
  }else {
      alert("DNI Inválido introducido");
    return false;
  }
}


