module TP1.Errors where

errorDemoraNegativa = error "El valor de la demora no puede ser negativo"
errorCantidadNegativa = error "El valor de la capacidad de túneles no puede ser negativo"
errorRepeticionCiudad = error "No es válido ingresar la misma ciudad en ambos argumentos"
errorLinks = error "No es válido ingresar una lista de links con un link repetido"
errorCiudadNuevaRepetida = error "No es válido añadir una ciudad que ya ha sido ingresada anteriormente"
errorLinkNuevoRepetido = error "No es válido añadir un link que ya ha sido ingresado anteriormente"
errorTunelNuevoRepetido = error "No es válido añadir un tunel que ya ha sido ingresado anteriormente"
errorNoConectadas = error "No es válido ingresar dos ciudades que no estén conectadas por un túnel"