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
	//var ul = document.createElement('ul');
	//ul.className = "columns-2";
	var table= document.createElement('table');
	var index = 0;
	for (index = 0; index < data.length; ++index) {
	    //var li = document.createElement('li');
	    var tr=document.createElement('tr');
	    var divMain = document.createElement('div');
	    divMain.className="col";
	    var divInfo = document.createElement('div');
	    var spanImage= document.createElement('span');
	    //li.className = "list-group-item";
	    spanImage.innerHTML="<img src="+data[index].image+"></img>";
	    divInfo.innerHTML="<h5>"+data[index].nom + "</h5><h4>" +data[index].prix+" €</h4>";
		//li.innerHTML = produitsStringify(data[index]);
		//console.log(produitsStringify(data[index]));
		divMain.appendChild(spanImage);
		divMain.appendChild(divInfo);
		tr.appendChild(divMain);
		table.appendChild(tr);
		//li.appendChild(divMain);
		//ul.appendChild(li);
	}
	//$("#boutique").html(ul);
	$("#boutique").html(table);
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
		li.innerHTML = userStringify(data[index]);
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