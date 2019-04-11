using AutoMapper;
using CloudPublishing.Business.Services.Interfaces;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace CloudPublishing.Controllers.RestApi
{
    public class PublishingsController : ApiController
    {
        private readonly IMapper mapper;
        private readonly IPublishingService publishingService;

        public PublishingsController(IMapper mapper, IPublishingService publishingService)
        {
            this.mapper = mapper;
            this.publishingService = publishingService;
        }

        public IHttpActionResult GetPublishings()
        {
            var publishings = publishingService.GetPublishings();
            return Ok(publishings.Select(p => new { p.Id, p.Title}));
        }

        public IHttpActionResult GetPublishings(int id)
        {
            var publishing = publishingService.GetPublishing(id);
            if (publishing != null)
            {
               return Ok(new { publishing.Id, publishing.Title });
            }
            else
            {
                return ResponseMessage(new HttpResponseMessage(HttpStatusCode.NoContent));
            }
        }

        public IHttpActionResult GetPublishings(string type)
        {
            var publishingsByType = publishingService.GetPublishingsByType(type);
            return Ok(publishingsByType.Select(p => new { p.Id, p.Title }));
        }
    }
}