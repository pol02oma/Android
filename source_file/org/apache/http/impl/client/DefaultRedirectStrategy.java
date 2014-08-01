package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultRedirectStrategy
  implements RedirectStrategy
{
  public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";
  private static final String[] REDIRECT_METHODS = { "GET", "HEAD" };
  private final Log log = LogFactory.getLog(getClass());

  private HttpUriRequest copyEntity(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase, HttpRequest paramHttpRequest)
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
      paramHttpEntityEnclosingRequestBase.setEntity(((HttpEntityEnclosingRequest)paramHttpRequest).getEntity());
    return paramHttpEntityEnclosingRequestBase;
  }

  protected URI createLocationURI(String paramString)
    throws ProtocolException
  {
    try
    {
      URI localURI = new URI(paramString).normalize();
      return localURI;
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    throw new ProtocolException("Invalid redirect URI: " + paramString, localURISyntaxException);
  }

  public URI getLocationURI(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    Header localHeader = paramHttpResponse.getFirstHeader("location");
    if (localHeader == null)
      throw new ProtocolException("Received redirect response " + paramHttpResponse.getStatusLine() + " but no location header");
    String str = localHeader.getValue();
    if (this.log.isDebugEnabled())
      this.log.debug("Redirect requested to location '" + str + "'");
    URI localURI1 = createLocationURI(str);
    HttpParams localHttpParams = paramHttpRequest.getParams();
    try
    {
      localObject = URIUtils.rewriteURI(localURI1);
      if (((URI)localObject).isAbsolute())
        break label305;
      if (localHttpParams.isParameterTrue("http.protocol.reject-relative-redirect"))
        throw new ProtocolException("Relative redirect location '" + localObject + "' not allowed");
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new ProtocolException(localURISyntaxException.getMessage(), localURISyntaxException);
    }
    HttpHost localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    if (localHttpHost == null)
      throw new IllegalStateException("Target host not available in the HTTP context");
    URI localURI2 = URIUtils.resolve(URIUtils.rewriteURI(new URI(paramHttpRequest.getRequestLine().getUri()), localHttpHost, true), (URI)localObject);
    Object localObject = localURI2;
    label305: RedirectLocations localRedirectLocations = (RedirectLocations)paramHttpContext.getAttribute("http.protocol.redirect-locations");
    if (localRedirectLocations == null)
    {
      localRedirectLocations = new RedirectLocations();
      paramHttpContext.setAttribute("http.protocol.redirect-locations", localRedirectLocations);
    }
    if ((localHttpParams.isParameterFalse("http.protocol.allow-circular-redirects")) && (localRedirectLocations.contains((URI)localObject)))
      throw new CircularRedirectException("Circular redirect to '" + localObject + "'");
    localRedirectLocations.add((URI)localObject);
    return (URI)localObject;
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    URI localURI = getLocationURI(paramHttpRequest, paramHttpResponse, paramHttpContext);
    String str = paramHttpRequest.getRequestLine().getMethod();
    if (str.equalsIgnoreCase("HEAD"))
      return new HttpHead(localURI);
    if (str.equalsIgnoreCase("GET"))
      return new HttpGet(localURI);
    if (paramHttpResponse.getStatusLine().getStatusCode() == 307)
    {
      if (str.equalsIgnoreCase("POST"))
        return copyEntity(new HttpPost(localURI), paramHttpRequest);
      if (str.equalsIgnoreCase("PUT"))
        return copyEntity(new HttpPut(localURI), paramHttpRequest);
      if (str.equalsIgnoreCase("DELETE"))
        return new HttpDelete(localURI);
      if (str.equalsIgnoreCase("TRACE"))
        return new HttpTrace(localURI);
      if (str.equalsIgnoreCase("OPTIONS"))
        return new HttpOptions(localURI);
      if (str.equalsIgnoreCase("PATCH"))
        return copyEntity(new HttpPatch(localURI), paramHttpRequest);
    }
    return new HttpGet(localURI);
  }

  protected boolean isRedirectable(String paramString)
  {
    String[] arrayOfString = REDIRECT_METHODS;
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (arrayOfString[j].equalsIgnoreCase(paramString))
        return true;
    return false;
  }

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    int i = 1;
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    int j = paramHttpResponse.getStatusLine().getStatusCode();
    String str = paramHttpRequest.getRequestLine().getMethod();
    Header localHeader = paramHttpResponse.getFirstHeader("location");
    switch (j)
    {
    case 304:
    case 305:
    case 306:
    default:
      i = 0;
    case 303:
    case 302:
      do
        return i;
      while ((isRedirectable(str)) && (localHeader != null));
      return false;
    case 301:
    case 307:
    }
    return isRedirectable(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.client.DefaultRedirectStrategy
 * JD-Core Version:    0.6.0
 */