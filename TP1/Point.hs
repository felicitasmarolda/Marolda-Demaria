module TP1.Point ( Point, newP, difP )
   where

data Point = Point Int Int
   deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Point

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Point a b) (Point c d) = sqrt ((fromIntegral (a)-fromIntegral (c))**2 + (fromIntegral (b)-fromIntegral (d))**2)