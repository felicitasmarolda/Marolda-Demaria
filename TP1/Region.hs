module TP1.Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR, buscaEnlaceCiudades )
   where

import TP1.Point
import TP1.City
import TP1.Link
import TP1.Quality
import TP1.Tunel
import Data.Bool (Bool(True))

data Region = Region [City] [Link] [Tunel]
newR :: Region
newR = Region [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Region cityList linkList tunelList) ciudadProxima = Region (ciudadProxima:cityList) linkList tunelList

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Region cityList linkList tunelList) ciudad1 ciudad2 calidad = Region cityList ((newLink ciudad1 ciudad2 calidad):linkList) tunelList

buscaEnlaceCiudades :: City -> City -> [Link] -> [Link]
buscaEnlaceCiudades ciudad1 ciudad2 [] = []
buscaEnlaceCiudades ciudad1 ciudad2 (x:xs) | ciudad1 == obtenerCiudad1 x && ciudad2 == obtenerCiudad2 x = [x]
                                           | ciudad1 == obtenerCiudad1 x && ciudad2 /= obtenerCiudad2 x = buscaEnlaceCiudades (obtenerCiudad2 x) ciudad2 (xs)
                                           | ciudad2 == obtenerCiudad1 x && ciudad1 == obtenerCiudad2 x = [x]
                                           | ciudad2 == obtenerCiudad1 x && ciudad1 /= obtenerCiudad1 x = buscaEnlaceCiudades (obtenerCiudad1 x) ciudad1 (xs)

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Region cityList linkList tunelList) dosCiudades | buscaEnlaceCiudades ((head (dosCiudades)) last ( dosCiudades) linkList) /= [] = Region ((cityList) linkList (newT (buscaEnlaceCiudades (head (dosCiudades) last ( dosCiudades) linkList)):tunelList))

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Region ciudades listLinks []) ciudad1 ciudad2 = False
connectedR (Region ciudades listLinks (tunel:listaTuneles)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel == True = True
                                                                            | otherwise = connectedR (Region ciudades listLinks (listaTuneles)) ciudad1 ciudad2

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Region ciudades [] tunelList) ciudad1 ciudad2 = False
linkedR (Region ciudades (enlace: listLinks) tunelList) ciudad1 ciudad2 | isLinked ciudad1 ciudad2 enlace = True
                                                                        | otherwise = linkedR (Region ciudades (listLinks) tunelList) ciudad1 ciudad2

encuentraTunel :: Region -> City -> City -> Tunel
--encuentraTunel (Region ciudades listLinks []) ciudad1 ciudad2 = (Tunel [])
encuentraTunel (Region ciudades listLinks (tunel:tunelList)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel == True = tunel
                                                                             | otherwise  = encuentraTunel (Region ciudades listLinks (tunelList)) ciudad1 ciudad2
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Region ciudades listLinks listTuneles) ciudad1 ciudad2 = delayT (encuentraTunel (Region ciudades listLinks listTuneles) ciudad1 ciudad2)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR = 0

pathR :: Int
pathR = 0

linksForR :: Int
linksForR = 0

usedCapacityForR :: Int
usedCapacityForR = 0