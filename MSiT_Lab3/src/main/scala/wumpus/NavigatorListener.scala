package wumpus

import java.util

trait NavigatorListener {
  def navigate(agentList: util.List[NavigatorAgent]): Unit
}
