/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function bloqueaUniversidad(){
    if($("#empresa").val() == '0'){
      
        $("#universidad").prop("disabled",true);
    }else{
        $("#universidad").prop("disabled",false);

    }
    
}
     
