package ma.enset;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SimpleServerContainer {
    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        String  encodedPRK="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAml2jGw5dt8A2HTMePMpbjcIgeTRpGj9Iq/xbbIcmpRiH6UmMb3AqNS2oqEr4qt5+zt0rIlxO0cuBnMX8Uug5sQIDAQABAkAAs0ljfLPdeuMZTCYS+IgPI0D/y8Zo9r3e/zdwTUdWRb2/rGhinhjRKLuXptHXP6PmslQJGAAXPHasdzrUz1oRAiEAxo1pOJC7HFnO7M6jDm8QcuPS5Sj3jGMUSLh7tymJPxMCIQDHB11oOY6CcZG+9pMdQpDJnoB1igIpQp14YO3jjIOIqwIhALMJGkMWRpJW7P6diWxfe66NfkKDAU0GnmjAaVYMzyQfAiBaoH3mAZ9kwnYNMJYYksD/Lc9stiT5yL2PnQN1w/v49QIgL+eQtqiWJoObVpt0k48pWa2KA7mUA4D2iYjJCb17YhY=";
        AgentController Agentserver= agentContainer.createNewAgent("Server","ma.enset.ServerAgent",new  Object[] {encodedPRK});
        Agentserver.start();

    }
}
