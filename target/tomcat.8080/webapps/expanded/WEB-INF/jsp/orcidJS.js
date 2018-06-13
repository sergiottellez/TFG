/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oauthWindow;

function openORCID() {
    var oauthWindow = window.open("https://orcid.org/oauth/authorize?client_id=APP-L3BFXY1PC98OL94Y&response_type=code&scope=/authenticate&redirect_uri=http://localhost:8080/TFGPruebaFinal/", "_blank", "toolbar=no, scrollbars=yes, width=500, height=600, top=500, left=500");
}


