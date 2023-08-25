module TP1.Point ( Point, newP, difP )
   where

data Point = Poi Int Int
   deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi a b) (Poi c d) = sqrt ((fromIntegral (a)-fromIntegral (c))**2 + (fromIntegral (b)-fromIntegral (d))**2)