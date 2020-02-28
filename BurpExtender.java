package burp;
import ModifyRequests.ModifyRequests;


public class BurpExtender implements IBurpExtender {

    public void registerExtenderCallbacks (IBurpExtenderCallbacks callbacks)
    {
        ModifyRequests RequestModifier = new ModifyRequests(callbacks);
        callbacks.setExtensionName(" HTTP Proxy Request Modifier");
        callbacks.registerHttpListener(RequestModifier);
    }
}

