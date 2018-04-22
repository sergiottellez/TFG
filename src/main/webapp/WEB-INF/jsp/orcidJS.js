/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oauthWindow;

function openORCID() {
    var oauthWindow = window.open("https://sandbox.orcid.org/oauth/authorize?client_id=0000-0002-1223-3173&response_type=code&scope=/authenticate&show_login=false&redirect_uri=http://localhost:8080/oauth-redirect.html", "_blank", "toolbar=no, scrollbars=yes, width=500, height=600, top=500, left=500");
}


