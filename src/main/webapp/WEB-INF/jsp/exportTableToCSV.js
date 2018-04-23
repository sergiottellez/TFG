/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}

function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        
        csv.push(row.join(","));        
    }

    // Download CSV file
    downloadCSV(csv.join("n"), filename);
}

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
                            .replace(/\n/g,'');
                    
      var link = document.createElement("a");
      link.download = "archivo.csv";
      link.href = "data:application/csv," + escape(datos);
      link.click;
                    
}       





function ExportToExcel(htmlExport) {
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");
    
    //other browser not tested on IE 11
    // If Internet Explorer
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))
    {
        jQuery('body').append(" <iframe id=\"iframeExport\" style=\"display:none\"></iframe>");
        iframeExport.document.open("txt/html", "replace");
        iframeExport.document.write(htmlExport);
        iframeExport.document.close();
        iframeExport.focus();
        sa = iframeExport.document.execCommand("SaveAs", true, "List.xls");
    }
    else {      
        var link = document.createElement('a');
    
        document.body.appendChild(link); // Firefox requires the link to be in the body
        link.download = "List.xls";
        link.href = 'data:application/vnd.ms-excel,' + escape(htmlExport);
        link.click();
        document.body.removeChild(link);
    }
}


