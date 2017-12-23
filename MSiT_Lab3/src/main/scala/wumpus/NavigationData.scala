package wumpus

class NavigationData {
  private var points = 0
  private var direction = 0
  private var x = 0
  private var y = 0

  def getPoints: Int = points

  def setPoints(points: Int): Unit = {
    this.points = points
  }

  def getDirection: Int = direction

  def setDirection(direction: Int): Unit = {
    this.direction = direction
  }

  def getX: Int = x

  def setX(x: Int): Unit = {
    this.x = x
  }

  def getY: Int = y

  def setY(y: Int): Unit = {
    this.y = y
  }
}
