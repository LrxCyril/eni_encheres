/**
 * JavaScript
 *@author Teddy
 *Script qui déactive un des 2 type radio lorsque l'un ou l'autre est sélectionné
 */

function radioDesactive(){
	if(inlineRadio2.onclick == true){
		
		document.getElementById("encheres-ouvertes").disabled = true;
		document.getElementById("mes-encheres").disabled = true;
		document.getElementById("mes-encheres-remportees").disabled = true;
		
	} 	else if(inlineRadio1 == true){
	
		document.getElementById("mes-ventes-en-cours").disabled = true;
		document.getElementById("ventes-non-debutees").disabled = true;
		document.getelementById("ventes-terminees").disabled = true;
	}
}
