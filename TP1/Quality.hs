module TP1.Quality ( Quality, newQ, capacityQ, delayQ )
   where

import TP1.Point
import TP1.City

data Quality = Qua String Int Float
   deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua nombre cantidad demora) = cantidad

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua nombre cantidad demora) = demora