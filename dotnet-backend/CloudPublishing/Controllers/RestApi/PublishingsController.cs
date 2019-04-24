using AutoMapper;
using CloudPublishing.Business.Services.Interfaces;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace CloudPublishing.Controllers.RestApi
{
    [RoutePrefix("api")]
    public class PublishingsController : ApiController
    {
        private readonly IMapper mapper;
        private readonly IPublishingService publishingService;

        public PublishingsController(IMapper mapper, IPublishingService publishingService)
        {
            this.mapper = mapper;
            this.publishingService = publishingService;
        }

        [HttpGet]
        public IHttpActionResult GetPublishings()
        {
            var publishings = publishingService.GetPublishings();
            if (publishings != null)
            {
                return Ok(publishings.Select(p => new { p.Id, p.Title }));
            }
            else
            {
                return StatusCode(HttpStatusCode.NoContent);
            }
        }

        [HttpGet]
        [Route("publishings/{id:int}")]
        public IHttpActionResult GetPublishings(int id)
        {
            var publishing = publishingService.GetPublishing(id);
            if (publishing != null)
            {
                return Ok(new { publishing.Id, publishing.Title });
            }
            else
            {
                return StatusCode(HttpStatusCode.NoContent);
            }
        }

        [HttpGet]
        public IHttpActionResult GetPublishings(string publishingType)
        {
            var publishingsByType = publishingService.GetPublishingsByType(publishingType);
            if (publishingsByType != null)
            {
                return Ok(publishingsByType.Select(p => new { p.Id, p.Title }));
            }
            else
            {
                return StatusCode(HttpStatusCode.NoContent);
            }
        }
    }
}