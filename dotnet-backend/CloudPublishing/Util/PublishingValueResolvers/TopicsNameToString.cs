using AutoMapper;
using CloudPublishing.Business.DTO;
using CloudPublishing.Models.Publishings;
using System.Linq;

namespace CloudPublishing.Util.PublishingValueResolvers
{
    public class TopicsNameToString : IValueResolver<PublishingDTO, PublishingTableViewModel, string>
    {
        public string Resolve(PublishingDTO source, PublishingTableViewModel destination, string destMember, ResolutionContext context)
        {
           return string.Join(", ", source.Topics.Select(x => x.Name));
        }
    }
}