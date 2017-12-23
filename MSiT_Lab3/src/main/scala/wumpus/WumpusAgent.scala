package wumpus

import jade.core.{AID, Agent}

class WumpusAgent extends Agent {
  def isPotholerReached = true


  def isPotholerExists(aid: AID) = true

  def execute(aid: AID, content: String): Unit = {
  }

  def isPotholer(aid: AID) = true
}
