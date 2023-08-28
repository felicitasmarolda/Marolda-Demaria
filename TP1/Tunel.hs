module TP1.Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import TP1.Point
import TP1.City
import TP1.Quality
import TP1.Link 
import TP1.Errors (errorLinks)

data Tunel = Tun [Link]
   deriving (Eq, Show)

newT :: [Link] -> Tunel
newT (link:linkList) | elem link linkList = errorLinks
                     | otherwise = Tun (link:linkList)

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conecta estas dos ciudades distintas
connectsT ciudad1 ciudad2 (Tun linkList)  | connectsL ciudad1 (head linkList) && not (connectsL ciudad1 (head (tail linkList))) && not (connectsL ciudad2 (last (init linkList))) && connectsL ciudad2 (last linkList) = True
                                          | connectsL ciudad2 (head linkList) && not (connectsL ciudad2 (head (tail linkList))) && not (connectsL ciudad1 (last (init linkList))) && connectsL ciudad1 (last linkList) = True
                                          | otherwise = False

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun []) = False
usesT link (Tun (x:xs))   | x == link = True
                          | otherwise = usesT link (Tun xs)

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun []) = 0
delayT (Tun (link:linkList)) = delayL link + delayT (Tun linkList)