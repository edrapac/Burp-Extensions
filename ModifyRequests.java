package ModifyRequests;

import burp.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ModifyRequests implements IHttpListener {


    IBurpExtenderCallbacks extenderCallbacks;
    IExtensionHelpers extenderHelpers;
    IRequestInfo requestInfo;
    List<String> headers = new ArrayList<>();

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) { //overrides the generic method for processing Http messages
        requestInfo = extenderHelpers.analyzeRequest(messageInfo);
        headers = requestInfo.getHeaders();
        IHttpService SERVICE = messageInfo.getHttpService();
        URL Url = requestInfo.getUrl();

        for (IParameter params:requestInfo.getParameters()){
            if(params.getName().equalsIgnoreCase("123flag123")
                    || params.getValue().equalsIgnoreCase("123flag123")
                    || messageInfo.getRequest().toString().contains("123flag123"))
            {
                ScannerIssue flag123 = new ScannerIssue(Url,messageInfo,SERVICE);
                if(extenderCallbacks.isInScope(Url)){
                    extenderCallbacks.addScanIssue(flag123);
                }
                else {
                    extenderCallbacks.printOutput("Request is out of scope: \n"+messageInfo.getRequest().toString());
                }

            }
        }

    }

    public ModifyRequests (IBurpExtenderCallbacks callbacks){
        extenderCallbacks = callbacks;
        extenderHelpers = callbacks.getHelpers();
    }
}
