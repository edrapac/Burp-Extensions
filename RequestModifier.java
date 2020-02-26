package burp;


class modifyProxy implements IHttpListener {

    IBurpExtenderCallbacks extenderCallbacks;
    IExtensionHelpers extenderHelpers;
    IRequestInfo requestInfo;

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) { //overrides the generic method for processing Http messages
        if(toolFlag == IBurpExtenderCallbacks.TOOL_PROXY && messageIsRequest == true) // checks if the request is from the proxy
            requestInfo = extenderHelpers.analyzeRequest(messageInfo); // returns IRequestInfo object
        if (requestInfo.getMethod()=="GET"){
            for (IParameter params:requestInfo.getParameters()){
                if (params.getName().equalsIgnoreCase("q")){
                    String base64param = extenderHelpers.base64Encode(params.getValue());
                    IParameter test = extenderHelpers.buildParameter("q",base64param,IParameter.PARAM_URL);

                    /*Wrap the below updateParameter call in a messageInfo.setRequest as the update method simply
                     * returns a request and does not forward the request on behalf of the client */

                    messageInfo.setRequest(extenderHelpers.updateParameter(messageInfo.getRequest(),test));
                }
            }
        }
    }

    public modifyProxy (IBurpExtenderCallbacks callbacks){ // constructor method
        extenderCallbacks = callbacks; // assigns the extenderCallbacks global the callbacks object data
        extenderHelpers = callbacks.getHelpers(); // assigns the extenderHelpers global the value of callbacks.getHelpers
    }
}

public class BurpExtender implements IBurpExtender {

    public void registerExtenderCallbacks (IBurpExtenderCallbacks callbacks)
    {
        callbacks.setExtensionName(" HTTP Proxy Request Modifier");
        callbacks.registerHttpListener(new burp.modifyProxy(callbacks));
    }
}

