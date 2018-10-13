/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function operacion(espacio) {
    var white = "#ffffff";
    var red = "#ff0000";
    var color = document.getElementById(espacio).getAttribute("fill");
    if (color === white) {
        document.getElementById(espacio).setAttribute("fill", red);
        document.getElementById(espacio).value = red;
    } else {
        document.getElementById(espacio).setAttribute("fill", white);
        document.getElementById(espacio).value = white;
    }
}
;

