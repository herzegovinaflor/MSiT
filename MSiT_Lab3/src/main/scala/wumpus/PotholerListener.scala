package wumpus

import java.util

trait PotholerListener {
  def execute(pothers: util.List[util.List[PotholerModel]]): Unit
}
