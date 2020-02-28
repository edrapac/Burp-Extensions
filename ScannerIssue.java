package ModifyRequests;

import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IScanIssue;

import java.net.URL;
import java.util.Scanner;

public class ScannerIssue implements IScanIssue {
    private URL issueUrl;
    private IHttpRequestResponse requestBody;
    private IHttpService services;
    @Override
    public URL getUrl() {
        return issueUrl;
    }

    @Override
    public String getIssueName() {
        return "123flag123";
    }

    @Override
    public int getIssueType() {
        return 0x08000000;
    }

    @Override
    public String getSeverity() {
        return "Information";
    }

    @Override
    public String getConfidence() {
        return "Certain";
    }

    @Override
    public String getIssueBackground() {
        return "Aaron thought it would be cool if we learned burp extensions lol";
    }

    @Override
    public String getRemediationBackground() {
        return "Joe Mama";
    }

    @Override
    public String getIssueDetail() {
        return "123flag123 found in either the Body or URL of the request!";
    }


    @Override
    public String getRemediationDetail() {
        return "Learn to code u skid";
    }

    @Override
    public IHttpRequestResponse[] getHttpMessages() {
        return new IHttpRequestResponse[]{requestBody};
    }

    @Override
    public IHttpService getHttpService() {
        return services;
    }

    public ScannerIssue(URL url, IHttpRequestResponse request, IHttpService service){
        issueUrl = url;
        requestBody = request;
        services = service;
    }
}