module Quality ( Quality, newQ, capacityQ, delayQ )
   where

import Point
import City

data Quality = Quality String Int Float 
   deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ nombre cantidad demora = Quality nombre cantidad demora
capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Quality nombre cantidad demora) = cantidad
delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Quality nombre cantidad demora) = demora