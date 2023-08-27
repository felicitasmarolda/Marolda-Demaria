module TP1.Link ( Link, newL, connectsL, linksL, capacityL, delayL )
   where

import TP1.Point
import TP1.City
import TP1.Quality

data Link = Lin City City Quality
   deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL ciudad (Lin ciudad1 ciudad2 calidad)  | ciudad == ciudad1 = True
                                                | ciudad == ciudad2 = True
                                                | otherwise = False

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL ciudad1 ciudad2 (Lin ciudadX ciudadY calidad)  | ciudad1 == ciudadX && ciudad2 == ciudadY = True
                                                      | ciudad2 == ciudadX && ciudad1 == ciudadY = True
                                                      | otherwise = False

capacityL :: Link -> Int
capacityL (Lin ciudad1 ciudad2 calidad) = capacityQ calidad

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin ciudad1 ciudad2 calidad) = (distanceC ciudad1 ciudad2) / (delayQ calidad)