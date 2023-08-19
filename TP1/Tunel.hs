module TP1.Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import TP1.Point
import TP1.City
import TP1.Quality
import TP1.Link

data Tunel = Tunel [Link] 
   deriving (Eq, Show)

newT :: [Link] -> Tunel
newT linkList = Tunel linkList

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conecta estas dos ciudades distintas
connectsT ciudad1 ciudad2 (Tunel linkList) | ciudad1 == obtenerCiudad1 (head linkList) && ciudad2 == obtenerCiudad2 (last linkList) = True
                                           | ciudad2 == obtenerCiudad1 (head linkList) && ciudad1 == obtenerCiudad2 (last linkList) = True
                                           | otherwise = False

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tunel []) = False
usesT link (Tunel (x:xs)) | x == link = True
                          | otherwise = (usesT link (Tunel (xs)))
delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tunel []) = 0
delayT (Tunel (link:linkList)) = delayLink link + delayT (Tunel (linkList))

