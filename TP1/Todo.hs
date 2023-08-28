module TP1.Todo where

import TP1.Point
import TP1.City
import TP1.Quality
import TP1.Link
import TP1.Tunel
import TP1.Region
import GHC.Exts.Heap (GenClosure(link))

p1 = newP 0 0
p2 = newP 3 4
p3 = newP 5 12
p4 = newP 8 6
p5 = newP (-1) (-1)

ciudad1 = newC "Londres" p1
ciudad2 = newC "Brasilia" p2
ciudad3 = newC "Sgo. del Estero" p3
ciudad4 = newC "Madrid" p4
ciudad5 = newC "Posadas" p5

calidad1 = newQ "1a2" 2 4.5
calidad2 = newQ "2a3" 4 5.0
calidad3 = newQ "3a4" 4 5.5
calidad4 = newQ "4a5" 2 6.0
calidad5 = newQ "5a1" 2 6.5

link1 = newL ciudad1 ciudad2 calidad1
link2 = newL ciudad2 ciudad3 calidad2
link3 = newL ciudad3 ciudad4 calidad3
link4 = newL ciudad4 ciudad5 calidad4
link5 = newL ciudad5 ciudad1 calidad5

tunela = newT [link2]
tunelb = newT [link3, link4, link5]
tunelc = newT [link5, link1, link2]
tuneld = newT [link4, link5, link1, link2, link3]


region = newR
regionCiudades = foundR (foundR (foundR (foundR (foundR region ciudad1) ciudad2) ciudad3) ciudad4) ciudad5
regionLinks = linkR (linkR (linkR (linkR (linkR regionCiudades ciudad1 ciudad2 calidad1) ciudad2 ciudad3 calidad2) ciudad3 ciudad4 calidad3) ciudad4 ciudad5 calidad4) ciudad5 ciudad1 calidad5
regionTuneles = tunelR( tunelR (tunelR (tunelR regionLinks [ciudad2, ciudad3]) [ciudad3, ciudad4, ciudad5, ciudad1]) [ciudad5, ciudad1, ciudad2, ciudad3]) [ciudad4, ciudad5, ciudad1, ciudad2, ciudad3, ciudad4]

t = [
    difP p1 p2 == 5,
    
    nameC ciudad1 == "Londres",
    distanceC ciudad1 ciudad2 == 5,

    capacityQ calidad3 == 4,
    delayQ calidad4 == 6.0,
    
    connectsL ciudad3 link3 == True,
    connectsL ciudad5 link1 == False,
    linksL ciudad4 ciudad5 link4 == True,
    linksL ciudad2 ciudad5 link1 == False,
    capacityL link2 == 4,
    delayL link1 == 5 / 4.5,

    connectsT ciudad3 ciudad1 tunelb == True,
    connectsT ciudad2 ciudad5 tuneld == False,
    usesT link3 tuneld == True,
    usesT link1 tunelb == False,
    delayT tunela == sqrt (68) / 5,
    
    linkedR regionTuneles ciudad3 ciudad4 == True,
    linkedR regionTuneles ciudad1 ciudad4 == False,
    delayR regionTuneles ciudad3 ciudad2 == (sqrt 68) / 5.0,
    connectedR regionTuneles ciudad2 ciudad5 == False,
    connectedR regionTuneles ciudad1 ciudad2 == True,
    connectedR regionTuneles ciudad4 ciudad2 == False]