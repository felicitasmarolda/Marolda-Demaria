module TP1.City ( City, newC, nameC, distanceC )
   where

import TP1.Point

data City = City String Point
   deriving (Eq, Show)

newC :: String -> Point -> City
newC = City

nameC :: City -> String
nameC (City name coords) = name

distanceC :: City -> City -> Float
distanceC (City name1 coords1) (City name2 coords2) = difP coords1 coords2