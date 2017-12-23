package wumpus

import java.util

trait NavigaListener {
  def navigate(agentList: util.List[NavigatorAgent]): Unit
}
