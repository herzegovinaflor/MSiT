package wumpus

import jade.content.lang.sl.SLCodec
import jade.core.AID
import jade.core.behaviours.SimpleBehaviour
import jade.lang.acl.ACLMessage

class GameBehaviour extends SimpleBehaviour {
  private val serialVersionUID = 1L

  private val ontology = null
  private var code = null

  override def onStart(): Unit = {
    val man = myAgent.getContentManager
    code = new SLCodec
    man.registerOntology(ontology)
    man.registerLanguage(code)
  }

  override def action(): Unit = {
    while ( {
      true
    }) {
      val aclMessage = myAgent.blockingReceive
      val content = aclMessage.getContent.trim
      val senderAID = aclMessage.getSender
      val aclReply = aclMessage.createReply
      aclReply.setPerformative(ACLMessage.REFUSE)
      aclReply.setOntology(ontology.getName)
      aclReply.setLanguage(code.getName)
      setPerformative(aclMessage, content, aclReply)
      processAgent(aclMessage, content, senderAID, aclReply)
      try
        Thread.sleep(2000)
      catch {
        case e: InterruptedException =>
          throw new RuntimeException(e)
      }
      myAgent.send(aclReply)
    }
  }

  private def setPerformative(msg: ACLMessage, content: String, reply: ACLMessage) = {
    if ((msg.getPerformative == ACLMessage.REQUEST) && (content == WumpusConstants.ACTION_REGISTER) && (!(myAgent.asInstanceOf[WumpusAgent]).isPotholerReached)) reply.setPerformative(ACLMessage.INFORM)
  }

  private def processAgent(msg: ACLMessage, content: String, senderAID: AID, reply: ACLMessage) = {
    if (isSuitable(msg, content, senderAID)) if (content == WumpusConstants.ACTION_DEREGISTER) {
      myAgent.asInstanceOf[WumpusAgent].execute(senderAID, content)
      reply.setPerformative(ACLMessage.AGREE)
    }
  }

  private def isSuitable(msg: ACLMessage, content: String, senderAID: AID) = (myAgent.asInstanceOf[WumpusAgent]).isPotholer(senderAID) && (msg.getPerformative == ACLMessage.REQUEST) && isContentValid(content) && (myAgent.asInstanceOf[WumpusAgent]).isPotholerExists(senderAID)

  private def isContentValid(content: String) = content == WumpusConstants.ACTION_DEREGISTER || content == WumpusConstants.ACTION_REGISTER || content == WumpusConstants.ACTION_GRAB || content == WumpusConstants.ACTION_MOVE || content == WumpusConstants.ACTION_SHOOT || content == WumpusConstants.ACTION_TURN_LEFT || content == WumpusConstants.ACTION_TURN_RIGHT

  override def onEnd = PotholerBehaviour.OVER

  override def done = Boolean.TRUE
}
