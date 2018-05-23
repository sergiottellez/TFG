/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  function makeTextFile (text) {
      var textFile = null;
    var data = new Blob([text], {type: 'text/plain'});

    // If we are replacing a previously generated file we need to
    // manually revoke the object URL to avoid memory leaks.
    if (textFile !== null) {
      window.URL.revokeObjectURL(textFile);
    }

    textFile = window.URL.createObjectURL(data);

    return textFile;
  };



   function generarLog() {
    var link = document.getElementById('downloadlink');
    var text = document.getElementById('textbox');
    console.log(text.value);
    link.href = makeTextFile(text.value);
    link.style.display = 'block';
  };


