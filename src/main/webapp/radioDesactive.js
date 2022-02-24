/**
 * JavaScript
 *@author Teddy
 *Script qui déactive un des 2 type radio lorsque l'un ou l'autre est sélectionné
 */

function radioDesactive(){
	if(inlineRadio2.onclick){
		
		document.getElementById("liste-achats").disabled = false;
		
		
	} 	else(inlineRadio1.onclick)
	
		document.getElementById("liste-ventes").disabled = false;
		
	
}
