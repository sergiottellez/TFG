/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function bloqueaUniversidad(){
    if($("#empresa").val() == "0"){
        $("#universidad").prop("disabled",true);
                $("#labelEmpresa").hide();

        $("#nombreEmpresa").hide();
    }else if($("#empresa").val() == "1"){
                
            $("universidad").val(" ");
        $("#universidad").prop("disabled",false);
        $("#labelEmpresa").show();
                $("#nombreEmpresa").show();
                

        
        

    }
    
}
     
