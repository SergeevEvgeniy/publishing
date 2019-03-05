using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using System.Web.Http.ModelBinding;
using CloudPublishing.Business.Services.Interfaces;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api/employees")]
    public class EmployeeController : ApiController
    {
        public EmployeeController()
        {
            
        }   
    }
}