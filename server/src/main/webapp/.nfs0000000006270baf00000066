var rangUser = "";
var id = 0;

function hideAll(){
    var root=$('#root');
    root.children().hide();
}

function getUser(name) {
	getUserGeneric(name, "v1/user/");
}

function getUserGeneric(name, url) {
	$.getJSON(url + name, function(data) {
		afficheUser(data);
	});
}

function login() {
	getWithAuthorizationHeader("v1/login", function(data){
	    $("#login_form").hide();
	    afficheUser(data);
	    if(data.nom === "admin"){
	    	rangUser = "admin";
	    	id = data.id;
	    	alert("Connecté en tant qu'administrateur");
	    	$("#afficheProfil").show();
	    	$("#deconnection").show();
	    	$("#afficheSeConnecter").hide();
	    	$("#afficheInscription").hide();
	    }else if(data.nom === "anonym"){
	    	alert("login incorrect");
	    }else{
	    	rangUser = "user";
	    	id = data.id;
	    	alert("Bienvenue " + data.prenom + " " + data.nom);
	    	$("#afficheProfil").show();
	    	$("#deconnection").show();
	    	$("#afficheSeConnecter").hide();
	    	$("#afficheInscription").hide();
	    }
	});
}

function profile() {
	getWithAuthorizationHeader("v1/profile", function (data) {afficheUser(data);});
}

 function getWithAuthorizationHeader(url, callback) {
 if($("#userlogin").val() != "") {
     $.ajax
     ({
       type: "GET",
       url: url,
       dataType: 'json',
       beforeSend : function(req) {
        req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
       },
       success: callback,
       error : function(jqXHR, textStatus, errorThrown) {
       			alert('error: ' + textStatus);
       		}
     });
     } else {
     $.getJSON(url, function(data) {
     	    afficheUser(data);
        });
     }
 }

function postUser(nom, prenom, email, adresse, tel,login, pwd) {
    postUserGeneric(nom, prenom, email,adresse,tel,login, pwd, 'v1/user/')
}

function postUserGeneric(nom,login,email,pwd,adresse,prenom,tel, url) {
	console.log("postUserGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"nom" : nom,
			"login" : login,
			"email" : email,
			"password" : pwd,
			"id" : 0,
			"adresse" : adresse,
			"prenom" : prenom,
			"tel" : tel 
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}
/*

function postcGeneric(nom,login,email,pwd,adresse,prenom,tel, url) {
	console.log("postUserGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"nom" : nom,
			"login" : login,
			"email" : email,
			"password" : pwd,
			"id" : 0,
			"adresse" : adresse,
			"prenom" : prenom,
			"tel" : tel 
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}*/

function listUsers() {
    listUsersGeneric("v1/user/");
}

function listUsersGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListUsers(data)
	});
}

function afficheUser(data) {
	console.log(data);
	$("#reponse").html(userStringify(data));
}

function afficheListUsers(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    var li = document.createElement('li');
	    li.className = "list-group-item";
		li.innerHTML = userStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function userStringify(user) {
    // return user.id + ". " + user.name + " &lt;" + user.email + "&gt;" + " (" + user.alias + ")";
    return " ID : "  + user.id +  " | Nom : " + user.nom  + " | Prénom : "+user.prenom+" | Email : " + user.email + " | Tel : "+ user.tel + " | Adresse : " + user.adresse + " | Login : " + user.login;
    // Inverser user.prenom et user.nom --> login stcoké dans prénom et inverssement.
}

function pageLogin(){
}


function handleImage(e){
    var reader = new FileReader();
    reader.onload = function(event){
        var img = new Image();
        img.onload = function(){
            canvas.width = img.width;
            canvas.height = img.height;
            ctx.drawImage(img,0,0);
        }
        img.src = event.target.result;
    }
    reader.readAsDataURL(e.target.files[0]);     
}

function pageProduit(){
	$('#reponse').show();
	produits();
}

function produits() {
	listProduitsGeneric("/v1/produit");
}

function listProduitsGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListProduit(data)
	});
}

function afficheListProduit(data) {
	var table= document.createElement('table');
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    var tr=document.createElement('tr');
	    var divMain = document.createElement('div');
	    var input=document.createElement('input');
	    input.value=data[index].id;
	    input.type="hidden";
	       
	    divMain.onclick = function(event){pageCommande(event.currentTarget);}
	    divMain.className="col";
	   
	    var divInfo = document.createElement('div');
	    var spanImage= document.createElement('span');
	    spanImage.innerHTML="<img src="+data[index].image+"></img>";
	    divInfo.innerHTML="<h5>"+data[index].nom + "</h5><h4>" +data[index].prix+" €</h4>";
	    divMain.appendChild(input);
		divMain.appendChild(spanImage);
		divMain.appendChild(divInfo);
		tr.appendChild(divMain);
		table.appendChild(tr);
	}
	$("#boutique").html(table);
}

function pageCommande(id){
	hideAll();
	commandeTextGeneric("v1/produit/"+id.children[0].value);
}

function commandeTextGeneric(url) {
	$.getJSON(url, function(data) {
		affichePageCommande(data);
	});
}

function affichePageCommande(data){
	$("#pageProduit").innerHTML="";
	$("#pageProduit").show();
	var table= document.createElement('table');
	table.innerHTML="<tr><td><img src="+data.image+"></img></td><td><h2>"+data.nom+"</h2><h3>"+data.prix+" €</h3><h4>"+data.description+"</h4><h2>Personnalisez votre commande :</h2>"+
	"<h4>Son prénom ?</h4><input type=text name=prenom id=prenom placeholder='Prénom ?' class=form-control/><h4>Sa photo</h4><h4>Dans quelle direction portera-t-il son regard ?</h4>"+
	"<input type=radio id=regard name=regard value=false><label for=regard>Regard à droite  </label>"+
	"<input type=radio id=regard1 name=regard value=true><label for=regard1>Regard à gauche</label>"+
	"<h4>Couleur du portrait</h4>"+
	"<input type=radio id=couleur name=couleur value=noir><label for=couleur>Noir</label>"+
	"<h4>Quelle typographie souhaitez vous ?</h4>"+
	"<input type=radio id=typo name=typo value=noir><label for=typo>1</label>"+
	"<h4>Format du portrait</h4>"+
	"<input type=radio id=portrait name=portrait value=false><label for=portrait>A4</label>"+
	"<input type=radio id=portrait1 name=portrait value=true><label for=portrait1>A3</label>"+
	"<br><button>Envoyer</button>"+
	"</td></tr>";

	$("#pageProduit").html(table.innerHTML);
}


function produitsText(){
    produitsTextGeneric("v1/produit");
}

function produitsTextGeneric(url) {
	$.getJSON(url, function(data) {
		
		afficheListProduitOld(data)
	});
}

function afficheListProduitOld(data) {
		var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    var li = document.createElement('li');
	    li.className = "list-group-item";
		li.innerHTML = produitsStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function produitsStringify(produits) {
    return produits.id + " / " + produits.nom + " / " + produits.description + " / " +produits.prix + "€";
}

function postProduits(nom, description, prix) {
    postProduitsGeneric(nom, description, prix , 'v1/produit');
}

function afficherProduits(data) {
	console.log(data);
	$("#reponse").html(userStringify(data));
}

function postProduitsGeneric(nom, description, prix, url) {
	console.log("postProduitsGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"nom": nom,
			"description": description,
			"prix": prix,
			"image":"-",
			"id":0
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postProduits error: ' + textStatus);
		}
	});
}

function listCommande() {
    listCommandeGeneric("v1/commande");
}

function listCommandeGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListCommande(data)
	});
}

function afficheListCommande(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    var li = document.createElement('li');
	    li.className = "list-group-item";
		li.innerHTML = commandeStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function commandeStringify(commande) {
    return "ID :" +commande.id + " | ID Client : " + commande.idClient + " | ID Produit : " + commande.idProduit + " | Prenom : " + commande.prenom + " | ID Image : " + commande.idImage + " | Regard : " + commande.regard + " | Couleur : " + commande.couleur + " | Typo : " + commande.typo + " | Format : " + commande.format;
}



function postCommande(idClient, idProduit, prenom, idImage, regard, couleur, typo, format) {
    postCommandeGeneric(idClient, idProduit, prenom, idImage, regard, couleur, typo, format , 'v1/commande');
}

function postCommandeGeneric(idClient, idProduit, prenom, idImage, regard, couleur, typo, format, url) {
	console.log("postProduitsGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"id":0,
			"idClient":idClient,
			"idProduit":idProduit,
			"prenom":prenom,
			"idImage":idImage,
			"regard": regard,
			"couleur": couleur,
			"typo": typo,
			"format":format
		}),
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postProduits error: ' + textStatus);
		}
	});
}

function whatIsThatUser(){
	return rangUser;
}

function whatIsUserId(){
	return id;
}

function getCommandeById(){
	listCommandeGeneric("v1/commande/user/"+whatIsUserId());
}

