module TP1.Link ( Link, newLink, ispartofLink, isLinked, capacityLink, delayLink )
   where

import TP1.Point
import TP1.City
import TP1.Quality

data Link = Link City City Quality 
   deriving (Eq, Show)


newLink :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newLink ciudad1 ciudad2 calidad = Link ciudad1 ciudad2 calidad

ispartofLink :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
ispartofLink (ciudad) (Link ciudad1 ciudad2 q) | ciudad == ciudad1 = True
                                               | ciudad == ciudad2 = True
                                               | otherwise = False
isLinked :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
isLinked ciudad1 ciudad2 (Link ciudadX ciudadY q) | ciudad1 == ciudadX && ciudad2 == ciudadY = True
                                                  | ciudad2 == ciudadX && ciudad1 == ciudadY = True
                                                  | otherwise = False

capacityLink :: Link -> Int
capacityLink (Link ciudad1 ciudad2 calidad) = capacityQ calidad

delayLink :: Link -> Float     -- la demora que sufre una conexion en este canal
delayLink (Link ciudad1 ciudad2 calidad) = delayQ calidad
