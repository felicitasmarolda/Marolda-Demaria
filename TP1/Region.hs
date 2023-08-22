module TP1.Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import TP1.Point
import TP1.City
import TP1.Quality
import TP1.Link
import TP1.Tunel

data Region = Region [City] [Link] [Tunel]
newR :: Region
newR = Region [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Region cityList linkList tunelList) ciudadProxima = Region (ciudadProxima:cityList) linkList tunelList

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Region cityList linkList tunelList) ciudad1 ciudad2 calidad = Region cityList ((newLink ciudad1 ciudad2 calidad):linkList) tunelList

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Region cityList linkList tunelList) (x:(y:(cityListX))) = Region cityList linkList (tunelList)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR r c1 c2 = True

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR r c1 c2 = True

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR r c1 c2 = 1

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR r c1 c2 = 1