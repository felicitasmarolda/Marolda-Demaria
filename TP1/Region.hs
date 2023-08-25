module TP1.Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR)
   where

import TP1.Point
import TP1.City
import TP1.Quality
import TP1.Link
import TP1.Tunel

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cityList linkList tunelList) ciudadProxima = Reg (ciudadProxima:cityList) linkList tunelList

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cityList linkList tunelList) ciudad1 ciudad2 calidad = Reg cityList ((newL ciudad1 ciudad2 calidad):linkList) tunelList

tunelR :: Region -> [City] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cityList linkList tunelList) (x:(y:(cityListX))) = Reg cityList linkList (tunelList)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg ciudades listLinks []) ciudad1 ciudad2 = False
connectedR (Reg ciudades listLinks (tunel:listaTuneles)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel == True = True
                                                                            | otherwise = connectedR (Reg ciudades listLinks (listaTuneles)) ciudad1 ciudad2

linkedR (Reg ciudades [] tunelList) ciudad1 ciudad2 = False
linkedR (Reg ciudades (enlace: listLinks) tunelList) ciudad1 ciudad2 | connectsL ciudad1 ciudad2 enlace = True
                                                                     | otherwise = linkedR (Reg ciudades (listLinks) tunelList) ciudad1 ciudad2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR r c1 c2 = 1

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR r c1 c2 = 1