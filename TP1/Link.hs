module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point
import City
import Quality

data Link = Link City City Quality 
   deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL ciudad1 ciudad2 calidad = Link ciudad1 ciudad2 calidad
connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL (ciudad) (Link ciudad1 ciudad2) | ciudad == ciudad1 = True
                                          | ciudad == ciudad2 = True
                                          | otherwise = False
linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL ciudad1 ciudad2 (Link ciudadX ciudadY) | ciudad1 == ciudadX && ciudad2 == ciudadY = True
                                              | ciudad2 == ciudadX && ciudad1 == ciudadY = True
                                              | otherwise = False
capacityL :: Link -> Int
capacityL (Link ciudad1 ciudad2 Quality newC capacidadC delayC) = cacpacidadC
delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Link ciudad1 ciudad2 Quality nombre cantidad demora ) = delayQ (Quality nombre cantidad demora)
obtenerCalidad :: Link -> Quality
obtenerCalidad (Link ciudad1 ciudad2 calidad) = calidad 