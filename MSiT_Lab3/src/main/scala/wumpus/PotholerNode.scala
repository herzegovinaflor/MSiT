package wumpus

import java.util

class PotholerNode {
  private var x = 0
  private var y = 0
  private var pit = false
  private var vampus = false
  private var isVampusAlive = false
  private var gold = false
  private var hunters = null

  def this(x: Int, y: Int) {
    this()
    this.x = x
    this.y = y
    this.pit = false
    this.vampus = false
    this.isVampusAlive = false
    this.gold = false
    this.hunters = new util.ArrayList[PotholerAgent]
  }

  def getX: Int = x

  def setX(x: Int): Unit = {
    this.x = x
  }

  def getY: Int = y

  def setY(y: Int): Unit = {
    this.y = y
  }

  def isPit: Boolean = pit

  def setPit(pit: Boolean): Unit = {
    this.pit = pit
  }

  def isVampus: Boolean = vampus

  def setVampus(vampus: Boolean): Unit = {
    this.vampus = vampus
  }

  def isVampusAlive: Boolean = isVampusAlive

  def setVampusAlive(vampusAlive: Boolean): Unit = {
    isVampusAlive = vampusAlive
  }

  def isGold: Boolean = gold

  def setGold(gold: Boolean): Unit = {
    this.gold = gold
  }

  def getHunters: util.List[PotholerAgent] = hunters

  def setHunters(hunters: util.List[PotholerAgent]): Unit = {
    this.hunters = hunters
  }

}
