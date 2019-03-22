using AutoMapper;
using CloudPublishing.Business.Services.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using System.Web.Http;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;

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

        public IHttpActionResult GetAllPublishings()
        {
            var result = publishingService.GetAllPublishings();
            return Json(mapper.Map<IEnumerable<PublishingDTO>, List<PublishingData>>(result),
                new JsonSerializerSettings { ContractResolver = new CamelCasePropertyNamesContractResolver() });
        }
    }
}