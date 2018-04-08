/* global fetch */
var activa;

function campoBusqueda(elem){

  campoTxt = document.getElementById('listar:busqueda');
  switch(elem){
    case "0":
      
     campoTxt.style.display='block';
     campoTxt.value = "Email";
    break;
    case "1":
    
    campoTxt.style.display='block';
     campoTxt.value = "Universidad";
    break;
    case "3":

      campoTxt.style.display='none';
    break;
    case "4":
 
      campoTxt.style.display='block';
     campoTxt.value = "Region";
    break;
    case "5":
   
    campoTxt.style.display='block';
     campoTxt.value = "Nombre";
    break;
    
  }
            }

            
          
     
   