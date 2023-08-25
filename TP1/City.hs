module TP1.City ( City, newC, nameC, distanceC )
   where

import TP1.Point

data City = Cit String Point
   deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit

nameC :: City -> String
nameC (Cit name coords) = name

distanceC :: City -> City -> Float
distanceC (Cit name1 coords1) (Cit name2 coords2) = difP coords1 coords2