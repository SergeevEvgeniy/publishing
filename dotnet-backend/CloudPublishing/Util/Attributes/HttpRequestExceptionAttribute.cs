using System;
using System.Net;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Http.Filters;
using CloudPublishing.Business.Resources.Messages;

namespace CloudPublishing.Util.Attributes
{
    public class HttpRequestExceptionAttribute : Attribute, IExceptionFilter
    {
        public bool AllowMultiple => true;

        public Task ExecuteExceptionFilterAsync(HttpActionExecutedContext actionExecutedContext,
            CancellationToken cancellationToken)
        {
            if (actionExecutedContext.Exception is HttpRequestException)
            {
                actionExecutedContext.Response = actionExecutedContext.Request.CreateErrorResponse(
                    HttpStatusCode.ServiceUnavailable, Error.UnavaibleRemoteHost);
            }

            return Task.FromResult<object>(null);
        }
    }
}