package wumpus

import java.util
import java.util.{ArrayList, List, Random}

class CavernEnvironment {
  private var potholers = null
  private var listenersCave = null

  private var hunters = null
  private var listenersHunter = null

  def this {
    this()
    potholers = new util.ArrayList[util.List[PotholerNode]]
    listenersCave = new util.ArrayList[PotholerListener]
    hunters = new util.ArrayList[NavigatorAgent]
    listenersHunter = new util.ArrayList[NavigatorListener]
    initCave()
  }

  private def initCave() = {
    createPotholerNode()
    setData()
    setPit()
  }

  private def setPit() = {
    var x = 0
    var y = 0
    x = 0
    while ( {
      x < potholers.size
    }) {
      y = 0
      while ( {
        y < potholers.get(x).size
      }) {
        setPit(x, y)

        {
          y += 1; y - 1
        }
      }

      {
        x += 1; x - 1
      }
    }
  }

  private def setPit(x: Int, y: Int) = {
    if ((x != 0 || y != 0) && potholers.get(x).get(y).getHunters.isEmpty) if (Math.random <= 0.2) potholers.get(x).get(y).setPit(true)
  }

  private def setData() = {
    val r = new Random
    var x = 0
    var y = 0
    var placed = false
    while ( {
      !placed
    }) {
      x = r.nextInt(potholers.size)
      y = r.nextInt(potholers.get(1).size)
      if (x != 0 || y != 0) {
        potholers.get(x).get(y).setVampus(true)
        placed = true
      }
    }
    placed = false
    while ( {
      !placed
    }) {
      x = r.nextInt(potholers.size)
      y = r.nextInt(potholers.get(1).size)
      if ((x != 0 || y != 0) && potholers.get(x).get(y).getHunters.isEmpty) {
        potholers.get(x).get(y).setGold(true)
        placed = true
      }
    }
  }

  private def createPotholerNode() = {
    var i = 0
    while ( {
      i < potholers.size
    }) {
      var j = 0
      while ( {
        j < potholers.get(i).size
      }) {
        potholers.get(i).add(new PotholerNode(i, j))

        {
          j += 1; j - 1
        }
      }

      {
        i += 1; i - 1
      }
    }
  }

}
