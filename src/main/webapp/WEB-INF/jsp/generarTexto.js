/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function descargarArchivo(contenidoEnBlob, nombreArchivo) {
  //creamos un FileReader para leer el Blob
  var reader = new FileReader();
  //Definimos la función que manejará el archivo
  //una vez haya terminado de leerlo
  reader.onload = function (event) {
    //Usaremos un link para iniciar la descarga 
    var save = document.createElement('a');
    save.href = event.target.result;
    save.target = '_blank';
    //Truco: así le damos el nombre al archivo 
    save.download = nombreArchivo || 'archivo.dat';
    var clicEvent = new MouseEvent('click', {
      'view': window,
      'bubbles': true,
      'cancelable': true
    });
    //Simulamos un clic del usuario
    //no es necesario agregar el link al DOM.
    save.dispatchEvent(clicEvent);
    //Y liberamos recursos...
    (window.URL || window.webkitURL).revokeObjectURL(save.href);
  };
  //Leemos el blob y esperamos a que dispare el evento "load"
  reader.readAsDataURL(contenidoEnBlob);
};

//Función de ayuda: reúne los datos a exportar en un
//solo objeto
/*function obtenerDatos(emails) {
  return {
    nombre: emails

  };
};
 */
//Genera un objeto Blob con los datos en un archivo TXT
function generarTexto(emails) {
     var texto = [];
 
  //texto.push(datos.nombre);
  //texto.push(';\n');
  var email = Object.values(emails);
  var i;
/*  for(i=0;i<email.length;i++){
      
      
      texto.push(email[i]);
      texto.push("; ");
      
  }
  
  */
 
 texto.push(email.toString());
  
  //El constructor de Blob requiere un Array en el primer 
  //parámetro así que no es necesario usar toString. El 
  //segundo parámetro es el tipo MIME del archivo
  return new Blob(texto, {
    type: 'text/plain'
  });
};
 
function descargartxt (emails) {
 // var datos = obtenerDatos();
  descargarArchivo(generarTexto(emails), 'posiblesBajas.txt');
  }



