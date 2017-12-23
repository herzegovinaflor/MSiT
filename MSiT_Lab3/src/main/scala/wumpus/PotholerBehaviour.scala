package wumpus

import jade.core.behaviours.FSMBehaviour

abstract class PotholerBehaviour extends FSMBehaviour {
  val STATE_REGISTER = "Register"
  val STATE_PLAY = "Play"

  val REGISTERED = 0
  val OVER = 1
  val NOT_REGISTERED = 2

  private[wumpus] def before(): Unit

  private[wumpus] def after(): Unit
}
