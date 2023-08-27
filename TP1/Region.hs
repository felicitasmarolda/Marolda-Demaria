module TP1.Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
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
<<<<<<< Updated upstream
connectedR (Reg cityList listLinks []) ciudad1 ciudad2 = False
connectedR (Reg cityList listLinks (tunel:listaTuneles)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel == True = True
                                                                            | otherwise = connectedR (Reg cityList listLinks (listaTuneles)) ciudad1 ciudad2
=======
connectedR (Reg ciudades listLinks []) ciudad1 ciudad2 = False
connectedR (Reg ciudades listLinks (tunel:listaTuneles)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel = True
                                                                            | otherwise = connectedR (Reg ciudades listLinks (listaTuneles)) ciudad1 ciudad2
>>>>>>> Stashed changes

linkedR (Reg cityList [] tunelList) ciudad1 ciudad2 = False
linkedR (Reg cityList (enlace: listLinks) tunelList) ciudad1 ciudad2 | connectsL ciudad1 ciudad2 enlace = True
                                                                     | otherwise = linkedR (Reg cityList (listLinks) tunelList) ciudad1 ciudad2

encuentraTunel :: Region -> City -> City -> Tunel
encuentraTunel (Reg cityList listLinks [ultimoTunel]) ciudad1 ciudad2 = ultimoTunel
encuentraTunel (Reg cityList listLinks (tunel:tunelList)) ciudad1 ciudad2 | connectsT ciudad1 ciudad2 tunel = tunel
                                                                          | otherwise = encuentraTunel (Reg cityList listLinks (tunelList)) ciudad1 ciudad2

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cityList linkList tunelList) ciudad1 ciudad2 = delayT (encuentraTunel (Reg cityList linkList tunelList) ciudad1 ciudad2)

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cityList linkList tunelList) ciudad1 ciudad2 = capacityL (encuentraLink (Reg cityList linkList tunelList) ciudad1 ciudad2) - (cuantosTuneles (Reg cityList linkList tunelList) (encuentraLink (Reg cityList linkList tunelList) ciudad1 ciudad2))

encuentraLink :: Region -> City -> City -> Link
encuentraLink (Reg cityList linkList tunelList) ciudad1 ciudad2 = head [i|i <- linkList, connectsL ciudad1 ciudad2 i]

cuantosTuneles :: Region -> Link -> Int
cuantosTuneles (Reg cityList linkList tunelList) link = length [i|i <- tunelList, usesT link i]
