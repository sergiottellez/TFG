/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var clientId = "APP-L3BFXY1PC98OL94Y";
var orcidAuthUrl = "https://qa.orcid.org/oauth/authorize";
var orcidCert = {"kty":"RSA","e":"AQAB","use":"sig","kid":"qa-orcid-org-r9afl7qf6hcg7g9ngszu5nt7gzf0ea6i","n":"wyMBMaxlHcZQ2tsbRWdmPF3IMf0rCj9y-NdxmZiuznQQkr0VTOfnf64WMJuhrORoBcFC_Y_G2ZHeDEag8fHjRoW0MVnX0rjcbbwCKenE3BNSsh3qwU1TJ-8SnZ_gsR1XFEqFvK371fE1qAw0UfqmEfVyMG07FZGQSUcUC8kVtXelgyhAlz4jbqW5SgnomlSYuwmcqKqj2ciib8713fePjujTPeuq7vpxXV7wmiHJ3aVyB5EWsX9dIm7JTknxceH9jjSM_JmeoszXX7oZqDcHUWoWbhmb4Ul4OZ25Eq7UQy48WLpTS5Ycz1FuEHuT8xUKDoL1L16293Ai-AY0dj2TDw"};
var pubKey = KEYUTIL.getKey(orcidCert);
function getFragmentParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\#&]" + name + "=([^&#]*)"),
        results = regex.exec(window.location.hash);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function checkSig(idToken){
  return KJUR.jws.JWS.verifyJWT(idToken, pubKey, {
    alg: ['RS256'], iss: ["https:\/\/orcid.org"] , aud:clientId,gracePeriod: 15*60 //15 mins skew allowed
  });
}
$(document).ready(function() {
  var id_token = getFragmentParameterByName("id_token");
  if(id_token){
    $("#login").hide();
    if (checkSig(id_token)){
      $("#info").text("SIGNATURE VERIFIED!");
      $("#contents").text(KJUR.jws.JWS.parse(id_token).payloadPP);
    }else{
      $("#info").text("INVALID TOKEN!");
      $("#contents").text(KJUR.jws.JWS.parse(id_token).payloadPP);
    }
  }else{
    $("#login").attr("href",orcidAuthUrl+"?response_type=token&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2F/example.html&client_id="+clientId+"&scope=openid&nonce=whatever");
  }
});
