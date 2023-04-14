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

public class SimpleClientContainer {
    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profileImpl=new ProfileImpl();
        profileImpl.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=runtime.createAgentContainer(profileImpl);
        String encodedPbk="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJpdoxsOXbfANh0zHjzKW43CIHk0aRo/SKv8W2yHJqUYh+lJjG9wKjUtqKhK+Krefs7dKyJcTtHLgZzF/FLoObECAwEAAQ==";
        AgentController AgentClient= agentContainer.createNewAgent("Client","ma.enset.ClientAgent",new  Object[] {encodedPbk});
        AgentClient.start();
    }
}
