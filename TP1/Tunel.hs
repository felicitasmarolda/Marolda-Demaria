import Point
import Link
import Quality
import City

module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

data Tunel = Tunel [Link] deriving (Eq, Show)

newTunel :: [Link] -> Tunel
newTunel listaLink = linkList

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT ciudad1 ciudad2 (Tunel linkList) = |
                                             |
usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel