package ua.nure.lab2;

import jade.content.ContentManager;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class GameBehaviour extends SimpleBehaviour {
    private static final long serialVersionUID = 1L;

    private Ontology ontology = null;
    private SLCodec code = null;

    public void onStart() {
        ContentManager man = myAgent.getContentManager();
        code = new SLCodec();
        man.registerOntology(ontology);
        man.registerLanguage(code);
    }

    @Override
    public void action() {
        while (true) {
            ACLMessage aclMessage = myAgent.blockingReceive();
            String content = aclMessage.getContent().trim();
            AID senderAID = aclMessage.getSender();
            ACLMessage aclReply = aclMessage.createReply();
            aclReply.setPerformative(ACLMessage.REFUSE);
            aclReply.setOntology(ontology.getName());
            aclReply.setLanguage(code.getName());
            setPerformative(aclMessage, content, aclReply);
            processAgent(aclMessage, content, senderAID, aclReply);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myAgent.send(aclReply);
        }
    }

    private void setPerformative(ACLMessage msg, String content, ACLMessage reply) {
        if ((msg.getPerformative() == ACLMessage.REQUEST)
                && (content.equals(WumpusConstants.ACTION_REGISTER))
                && (!((WumpusAgent) myAgent).isPotholerReached()))
            reply.setPerformative(ACLMessage.INFORM);
    }

    private void processAgent(ACLMessage msg, String content, AID senderAID, ACLMessage reply) {
        if (isSuitable(msg, content, senderAID)) {
            if (content.equals(WumpusConstants.ACTION_DEREGISTER)) {
                ((WumpusAgent) myAgent).execute(senderAID, content);
                reply.setPerformative(ACLMessage.AGREE);
            }
        }
    }

    private boolean isSuitable(ACLMessage msg, String content, AID senderAID) {
        return (((WumpusAgent) myAgent).isPotholer(senderAID))
                && (msg.getPerformative() == ACLMessage.REQUEST)
                && (isContentValid(content))
                && (((WumpusAgent) myAgent).isPotholerExists(senderAID));
    }

    private boolean isContentValid(String content) {
        return content.equals(WumpusConstants.ACTION_DEREGISTER)
                || content.equals(WumpusConstants.ACTION_REGISTER)
                || content.equals(WumpusConstants.ACTION_GRAB)
                || content.equals(WumpusConstants.ACTION_MOVE)
                || content.equals(WumpusConstants.ACTION_SHOOT)
                || content.equals(WumpusConstants.ACTION_TURN_LEFT)
                || content.equals(WumpusConstants.ACTION_TURN_RIGHT);

    }

    public int onEnd() {
        return PotholerBehaviour.OVER;
    }

    public boolean done() {
        return Boolean.TRUE;
    }
}
