/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function crearCSV(){
    
    
    
    var tablehtml = document.getElementById("tabletocsv").innerHTML;
    
    
    var datos = tablehtml.replace(/<thead>/g,'')
                            .replace(/<\/thead>/g,'')
                            .replace(/<tbody>/g,'')
                            .replace(/<\/tbody>/g,'')
                            .replace(/<tr>/g,'')
                            .replace(/<\/tr>/g,'\r\n')
                            .replace(/<th>/g,'')
                            .replace(/<\/th>/g,';')
                            .replace(/<td>/g,'')
                            .replace(/<\/td>/g,';')
                            .replace(/\t/g,'')
                            .replace(/\n/g,'')
                            .replace(/<a>/g,'')
                            .replace(/<\/a>/g,'');
                    
      var link = document.createElement("a");
      link.download = "archivo.csv";
      link.href = "data:application/csv," + escape(datos);
      link.click();
                    
}       









