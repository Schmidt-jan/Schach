case class Element(val x : Int, val y: Int) {

}
val field = Vector(Element(7, 4), Element(3, 0), Element(1, 5))

var xFrom = 7
var yFrom = 7
var xTo = 7
var yTo = 1

if (xFrom == xTo && yFrom == yTo) print("Fehler")

//horizontal move
if (xFrom == xTo) {
  var incY = 1
  if (yFrom > yTo) incY = -1

  for(y <- Range(yFrom, yTo, incY)){
    if (!field.exists(input => (input.y == y && input.x  == xFrom))) printf("Ok, x:%d y:%d\n", xFrom, y)
    else printf("Fehler, x:%d y:%d\n", xFrom, y)
  }

}
//horizontal move
else if (yFrom == yTo) {
  var incX = 1;
  if (xFrom > xTo) incX = -1

  for(x <- Range(xFrom, xTo, incX)) {
    if(!field.exists(input => (input.x == x && input.y == yFrom))) printf("Ok, x:%d y:%d\n", x, yFrom)
    else printf("Fehler, x:%d y:%d\n", x, yFrom)
  }
} else print("Fehler\n")

