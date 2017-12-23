package actors

import akka.actor.Actor
import akka.actor.SupervisorStrategy.Stop
import jdk.nashorn.internal.runtime.Undefined

class PreOrder(n: Tree) extends Producer[Int] {
  def produceValues = traverse(n)

  def traverse(n: Tree) {
    if (n != null) {
      produce(n.elem)
      traverse(n.left)
      traverse(n.right)
    }
  }
}

abstract class Producer[T] {
  protected def produceValues: unit
  private val Undefined = new Object

  protected def produce(x: T) {
    coordinator ! Some(x)
    receive { case Next => }
  }

  private val producer: Actor = actor {
    receive {
      case Next =>
        produceValues
        coordinator ! None
    }
  }

  private val coordinator: Actor = actor {
    loop {
      react {
        case Next =>
          producer ! Next
          reply {
            receive { case x: Option[_] => x }
          }
        case Stop => exit('stop)
      }
    }
  }

  def iterator = new Iterator[T] {
    private var current: Any = Undefined
    private def lookAhead = {
      if (current == Undefined) current = coordinator !? Next
      current
    }

    def hasNext: Boolean = lookAhead match {
      case Some(x) => true
      case None => { coordinator ! Stop; false }
    }

    def next: T = lookAhead match {
      case Some(x) => current = Undefined; x.asInstanceOf[T]
    }
  }
}