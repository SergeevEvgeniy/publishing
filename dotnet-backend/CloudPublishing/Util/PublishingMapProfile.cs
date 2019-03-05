using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;

namespace CloudPublishing.Util
{
    public class PublishingMapProfile : Profile
    {
        public PublishingMapProfile()
        {
            CreateMap<PublishingDTO, PublishingTableViewModel>();
        }
    }

    public class PublishingTypeFormatter : IValueResolver<PublishingDTO, PublishingTableViewModel, string>
    {
        public string Resolve(PublishingDTO source, PublishingTableViewModel destination, string destMember, ResolutionContext context)
        {
            string type = "";
            if(source.Type == "m")
            {
                type = "Журнал";
            }
            else if(source.Type == "n")
            {
                type = "Газета";
            }
            return type;
        }
    }
}